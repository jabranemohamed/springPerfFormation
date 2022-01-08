package com.hibernate4perf.movies.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    protected Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((!(obj instanceof Genre))) {
            return false;
        }
        Genre other = (Genre) obj;
        return Objects.equals(name, other.getName());
    }

    @Override
    public String toString() {
        return "Genre [id=" + id + ", name=" + name + "]";
    }

}
