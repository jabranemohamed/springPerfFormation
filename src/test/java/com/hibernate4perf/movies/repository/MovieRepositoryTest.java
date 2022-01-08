package com.hibernate4perf.movies.repository;

import com.hibernate4perf.movies.domain.*;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql({"/data/data-test.sql"})
public class MovieRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieRepositoryTest.class);

    @Autowired
    private MovieRepository repository;

    @Test
    public void Review_ratingValidation() {
        Review review1 = new Review().setAuthor("max").setContent("super film !").setRating(12);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Review>> errors = validator.validate(review1);
        assertThat(errors).as("le rating aurait du provoquer une erreur").hasSize(1);
    }

    @Test
    public void createMovie_withAward() {
        Award award = new Award().setName("Best Motion Picture of the Year").setYear(2011);
        Movie movie = new Movie().setName("Inception")
                .setCertification(Certification.INTERDIT_MOINS_12)
                .addAward(award);
        repository.save(movie);
        assertThat(award.getId()).as("Award aurait du être persisté avec Movie").isNotNull();
    }

    @Test
    public void save_casNominal() {
        Movie movie = new Movie().setName("Inception")
                .setDescription("test")
                .setCertification(Certification.INTERDIT_MOINS_12);
        repository.save(movie);
        assertThat(movie.getId()).as("le movie aurait du être persisté").isNotNull();
    }

    @Test
    public void save_withTransientGenres_shouldNotBeAllowed() {
        assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            Movie movie = new Movie();
            movie.setName("The Social Network");
            Genre bio = new Genre("Biography");
            Genre drama = new Genre("Drama");
            movie.addGenre(bio).addGenre(drama);
            repository.save(movie);
            assertThat(bio.getId()).as("l'entité aurait du être persistée avec le Movie").isNotNull();
        });
    }

    @Test
    public void merge_withExistingGenre() {
        Movie movie = new Movie();
        movie.setName("The Social Network");
        repository.save(movie);
        Genre action = new Genre("Action");
        action.setId(-1L);
        movie.addGenre(action);
        Movie m = repository.save(movie);
        assertThat(m.getGenres()).as("l'entité persistée devrait avoir 1 genre").hasSize(1);
        assertThat(m.getGenres()).as("tous les genres associés à l'entité devrait avoir un id").allMatch(g -> g.getId() != null);
    }

    @Test
    public void associationSave_casNominal() {
        Movie movie = new Movie().setName("Fight Club")
                .setCertification(Certification.INTERDIT_MOINS_12)
                .setDescription("Le fight club n'existe pas");
        Review review1 = new Review().setAuthor("max").setContent("super film !");
        Review review2 = new Review().setAuthor("jp").setContent("au top!");
        movie.addReview(review1);
        movie.addReview(review2);
        repository.save(movie);
        assertThat(review1.getId()).as("les reviews aurait du être persistées par cascade").isNotNull();
    }

    @Test
    public void associationGet_casNominal() {
        assertThrows(LazyInitializationException.class, () -> {
            Movie movie = repository.findById(-1L).get();
            LOGGER.trace("nombre de reviews : " + movie.getReviews().size());
        });
    }

    @Test
    public void update_casNotFound() {
        assertThrows(JpaObjectRetrievalFailureException.class, () -> {
            Movie movie = new Movie();
            movie.setId(-10L);
            movie.setName("Inception 2");
            repository.update(movie);
        });
    }

    @Test
    public void merge_casSimule() {
        Movie movie = new Movie();
        movie.setName("Inception 2");
        movie.setId(-1L);
        Movie mergedMovie = repository.save(movie);
        assertThat(mergedMovie.getName()).as("le nom du film n'a pas été mis à jour").isEqualTo("Inception 2");
    }

    @Test
    public void find_casNominal() {
        Movie memento = repository.findById(-2L).get();
        assertThat(memento.getName()).as("mauvais film récupéré").isEqualTo("Memento");
        assertThat(memento.getCertification()).as("le converter n'a pas fonctionné")
                .isEqualTo(Certification.INTERDIT_MOINS_12);
    }

    @Test
    public void getAll_casNominal() {
        List<Movie> movies = repository.findAll();
        assertThat(movies).as("l'ensemble des films n'a pas été récupéré").hasSize(2);
    }

    @Test
    public void remove_casNominal() {
        repository.deleteById(-2L);

        List<Movie> movies = repository.findAll();
        assertThat(movies).as("le film n'a pas été supprimé").hasSize(1);
    }

    @Test
    public void getReference_casNominal() {
        Movie movie = repository.getOne(-2L);
        assertThat(movie.getId()).as("la référence n'a pas été correctement chargée").isEqualTo(-2L);
    }

    @Test
    public void getReference_fail() {
        assertThrows(LazyInitializationException.class, () -> {
            Movie movie = repository.getOne(-2L);
            LOGGER.trace("movie name : " + movie.getName());
            assertThat(movie.getId()).as("la référence n'a pas été correctement chargée").isEqualTo(-2L);
        });
    }

    @Test
    public void findByName() {
        List<Movie> result = repository.findByName("Inception");
        assertThat(result).as("il ne devrait y avoir qu'un film correspondant au critère").hasSize(1);
        assertThat(result.get(0).getName()).as("mauvais film récupéré").isEqualTo("Inception");
    }

    @Test
    public void findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase_casNominal() {
        List<Movie> result = repository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase("cept", "cept");
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Inception");
    }

    @Test
    public void findWithCertificatqueryion_casNominal() {
        List<Movie> result = repository.findWithCertification("<=", Certification.INTERDIT_MOINS_12);
        assertThat(result).as("il devrait y avoir 2 films correspondants au critère de recherche").hasSize(2);
    }

    @Test
    public void getMoviesWithAwardsAndReviews_casNominal() {
        List<Movie> m = repository.getMoviesWithAwardsAndReviews();
        assertThat(m).as("il devrait y avoir 2 films récupérés").hasSize(2);
        Movie inception = m.stream().filter(movie -> movie.getId().equals(-1L)).findFirst().get();
        assertThat(inception.getReviews()).as("les reviews n'ont pas été correctement récupérées").hasSize(2);
        assertThat(inception.getAwards()).as("les awards n'ont pas été correctement récupérées").hasSize(4);
    }

    @Test
    @Sql({"/data/data-test-bulk-1M.sql"})
    public void getAllMovieBulk_casNominal() {
        Instant now = Instant.now();
        List<Movie> movies = repository.findAll(PageRequest.of(2, 50, Sort.by("name"))).getContent();
        Duration duration = Duration.between(now, Instant.now());
        LOGGER.info("durée : {}", duration.toMillis());
        assertThat(movies).hasSize(50);
    }
}
