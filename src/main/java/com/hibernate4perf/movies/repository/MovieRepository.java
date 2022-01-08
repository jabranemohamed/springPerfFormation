package com.hibernate4perf.movies.repository;

import com.hibernate4perf.movies.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long>, MovieRepositoryExtended {

    List<Movie> findByName(String searchString);

    List<Movie> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String desc);
}
