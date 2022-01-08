package com.hibernate4perf.movies.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;

    private String author;

    private String content;

    @Min(value = 0)
    @Max(value = 10)
    private Integer rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private Movie movie;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public Review setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Review setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getRating() {
        return rating;
    }

    public Review setRating(Integer rating) {
        this.rating = rating;
        return this;
    }

    @Override
    public int hashCode() {
        return 32;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((!(obj instanceof Movie))) {
            return false;
        }
        Review other = (Review) obj;
        if (id == null && other.getId() == null) {
            return Objects.equals(author, other.getAuthor()) && Objects.equals(content, other.getContent())
                    && Objects.equals(rating, other.getRating());
        }
        return id != null && Objects.equals(id, other.getId());
    }

    @Override
    public String toString() {
        return "Review [id=" + id + ", author=" + author + ", content=" + content + "]";
    }

}
