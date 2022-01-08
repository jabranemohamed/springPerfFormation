package com.hibernate4perf.movies.repository;

import com.hibernate4perf.movies.domain.Certification;
import com.hibernate4perf.movies.domain.Movie;

import java.util.List;

public interface MovieRepositoryExtended {

    Movie update(Movie movie);

    List<Movie> findWithCertification(String operation, Certification certif);

    List<Movie> getMoviesWithAwardsAndReviews();

}