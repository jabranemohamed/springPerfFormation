package com.hibernate4perf.movies.repository;

import com.hibernate4perf.movies.domain.MovieDetails;

public interface MovieDetailsRepositoryExtended {

    void addMovieDetails(MovieDetails movieDetails, Long idMovie);

    MovieDetails getMovieDetails(Long id);
}
