package com.hibernate4perf.movies.repository;

import com.hibernate4perf.movies.domain.MovieDetails;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieDetailsRepository extends JpaRepository<MovieDetails, Long>, MovieDetailsRepositoryExtended {

    @Override
    @EntityGraph(attributePaths = {"movie"})
    List<MovieDetails> findAll();

}
