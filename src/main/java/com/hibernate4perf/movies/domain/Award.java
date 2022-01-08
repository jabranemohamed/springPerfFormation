package com.hibernate4perf.movies.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;

    private String name;

    private int year;

    @ManyToOne
    @JsonIgnore
    private Movie movie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Award setName(String name) {
        this.name = name;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Award setYear(int year) {
        this.year = year;
        return this;
    }

    public Movie getMovie() {
        return movie;
    }

    public Award setMovie(Movie movie) {
        this.movie = movie;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(62);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((!(obj instanceof Award))) {
            return false;
        }
        Award other = (Award) obj;
        if (id == null && other.getId() == null) {
            return Objects.equals(name, other.getName()) && Objects.equals(year, other.getYear());
        }
        return id != null && Objects.equals(id, other.getId());
    }

    @Override
    public String toString() {
        return "Award [id=" + id + ", name=" + name + ", year=" + year + "]";
    }

}
