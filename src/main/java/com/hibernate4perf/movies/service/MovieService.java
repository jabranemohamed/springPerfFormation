package com.hibernate4perf.movies.service;


import com.hibernate4perf.movies.domain.Movie;
import com.hibernate4perf.movies.repository.GenreRepository;
import com.hibernate4perf.movies.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    private final GenreRepository genreRepository;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    @Transactional
    public void updateDescription(Long id, String description) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            movie.get().setDescription(description);
        }
    }
}
