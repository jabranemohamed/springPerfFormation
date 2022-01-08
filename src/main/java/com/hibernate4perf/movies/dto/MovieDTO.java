package com.hibernate4perf.movies.dto;

import com.hibernate4perf.movies.domain.Certification;

public class MovieDTO {
    private Long id;

    private String name;

    private String description;

    private Certification certification;

    private String image;

    public Long getId() {
        return id;
    }

    public MovieDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MovieDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MovieDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Certification getCertification() {
        return certification;
    }

    public MovieDTO setCertification(Certification certification) {
        this.certification = certification;
        return this;
    }

    public String getImage() {
        return image;
    }

    public MovieDTO setImage(String image) {
        this.image = image;
        return this;
    }

}
