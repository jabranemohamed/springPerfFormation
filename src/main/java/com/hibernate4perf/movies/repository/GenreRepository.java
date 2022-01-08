package com.hibernate4perf.movies.repository;

import com.hibernate4perf.movies.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
