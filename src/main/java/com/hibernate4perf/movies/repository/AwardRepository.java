package com.hibernate4perf.movies.repository;

import com.hibernate4perf.movies.domain.Award;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AwardRepository extends JpaRepository<Award, Long> {

    List<Award> findByYear(int year);
}
