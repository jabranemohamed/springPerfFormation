package com.hibernate4perf.movies.repository;

import com.hibernate4perf.movies.domain.Movie;
import com.hibernate4perf.movies.domain.MovieDetails;
import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MovieDetailsRepositoryExtendedImpl implements MovieDetailsRepositoryExtended {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void addMovieDetails(MovieDetails movieDetails, Long idMovie) {
        Movie movieRef = entityManager.getReference(Movie.class, idMovie);
        movieDetails.setMovie(movieRef);
        entityManager.persist(movieDetails);
    }

    @Override
    @Transactional(readOnly = true)
    public MovieDetails getMovieDetails(Long id) {
        MovieDetails result = entityManager
                .createQuery("select distinct md from MovieDetails md " + "join fetch md.movie m "
                        + "left join fetch m.reviews "
                        + "left join fetch m.genres "
                        + " where md.id = :id", MovieDetails.class)
                .setParameter("id", id)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getSingleResult();

        entityManager
                .createQuery("select distinct m from Movie m left join fetch m.awards where m in (:movies)",
                        Movie.class)
                .setParameter("movies", result.getMovie())
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();

        return result;
    }

}
