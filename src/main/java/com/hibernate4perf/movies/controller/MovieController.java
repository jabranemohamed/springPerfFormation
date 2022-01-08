package com.hibernate4perf.movies.controller;

import com.hibernate4perf.movies.domain.Movie;
import com.hibernate4perf.movies.domain.MovieDetails;
import com.hibernate4perf.movies.dto.MovieDTO;
import com.hibernate4perf.movies.repository.MovieDetailsRepository;
import com.hibernate4perf.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private MovieDetailsRepository movieDetailsRepository;

    @PostMapping("/")
    public Movie create(@RequestBody Movie movie) {
        repository.save(movie);
        return movie;
    }

    @GetMapping("/")
    public ResponseEntity<List<MovieDTO>> get() {
        List<Movie> movie = repository.findAll();
        List<MovieDTO> moviesDTO = new ArrayList<>();
        toMoviesDTO(movie, moviesDTO);

        return ResponseEntity.ok(moviesDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieDTO>> get(@ModelAttribute("text") String text) {
        List<Movie> movie = repository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(text, text);
        List<MovieDTO> moviesDTO = new ArrayList<>();
        toMoviesDTO(movie, moviesDTO);

        return ResponseEntity.ok(moviesDTO);
    }

    private void toMoviesDTO(List<Movie> movie, List<MovieDTO> moviesDTO) {
        movie.forEach(m -> moviesDTO.add(new MovieDTO().setCertification(m.getCertification())
                .setDescription(m.getDescription())
                .setId(m.getId())
                .setImage(m.getImage())
                .setName(m.getName())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDetails> get(@PathVariable("id") Long id) {
        ResponseEntity<MovieDetails> result;
        try {
            MovieDetails movieDetails = movieDetailsRepository.getMovieDetails(id);
            if (movieDetails == null) {
                result = ResponseEntity.notFound().build();
            } else {
                result = ResponseEntity.ok(movieDetails);
            }
        } catch (EmptyResultDataAccessException exc) {
            result = ResponseEntity.notFound().build();
        }
        return result;
    }

    @PutMapping("/")
    public ResponseEntity<Movie> update(@RequestBody Movie movie) {
        try {
            // nb: ceci est une façon de faire différente de ce que j'ai fait dans la vidéo
            Movie result = repository.save(movie);
            return ResponseEntity.ok(result);
        } catch (EntityNotFoundException exc) {
            return ResponseEntity.notFound().build();
        } catch (ObjectOptimisticLockingFailureException exc) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Movie> delete(@PathVariable("id") Long id) {
        Optional<Movie> movie = repository.findById(id);
        if (movie.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
