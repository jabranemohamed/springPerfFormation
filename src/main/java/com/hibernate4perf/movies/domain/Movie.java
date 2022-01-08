package com.hibernate4perf.movies.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Version
    @JsonProperty
    private short version;

    private String description;

    private Certification certification;

    private String image;

    private String director;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "movie")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Award> awards = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    // utile pour jackson
    public short getVersion() {
        return version;
    }

    public Movie addGenre(Genre genre) {
        if (genre != null) {
            this.genres.add(genre);
        }
        return this;
    }

    public Movie removeGenre(Genre genre) {
        if (genre != null) {
            this.genres.remove(genre);
        }
        return this;
    }

    public Set<Genre> getGenres() {
        return Collections.unmodifiableSet(genres);
    }

    public Movie addReview(Review review) {
        if (review != null) {
            this.reviews.add(review);
            review.setMovie(this);
        }
        return this;
    }

    public Movie removeReview(Review review) {
        if (review != null) {
            this.reviews.remove(review);
            review.setMovie(null);
        }
        return this;
    }

    public List<Review> getReviews() {
        return Collections.unmodifiableList(reviews);
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Certification getCertification() {
        return certification;
    }

    public Movie setCertification(Certification certification) {
        this.certification = certification;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Movie setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Movie setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Movie setImage(String image) {
        this.image = image;
        return this;
    }

    public String getDirector() {
        return director;
    }

    public Movie setDirector(String director) {
        this.director = director;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(31);
    }

    public List<Award> getAwards() {
        return Collections.unmodifiableList(awards);
    }

    public Movie addAward(Award award) {
        if (award != null) {
            this.awards.add(award);
            award.setMovie(this);
        }
        return this;
    }

    public Movie removeAward(Award award) {
        if (award != null) {
            this.awards.remove(award);
            award.setMovie(null);
        }
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((!(obj instanceof Movie))) {
            return false;
        }
        Movie other = (Movie) obj;
        if (id == null && other.getId() == null) {
            return Objects.equals(name, other.getName()) && Objects.equals(description, other.getDescription())
                    && Objects.equals(certification, other.getCertification())
                    && Objects.equals(director, other.getDirector()) && Objects.equals(image, other.getImage());
        }
        return id != null && Objects.equals(id, other.getId());
    }

    @Override
    public String toString() {
        return "Movie [id=" + id
                + ", name="
                + name
                + ", description="
                + description
                + ", certification="
                + certification
                + ", image="
                + image
                + ", director="
                + director
                + "]";
    }

}
