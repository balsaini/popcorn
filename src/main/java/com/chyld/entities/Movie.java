package com.chyld.entities;

import com.chyld.enums.Genre;
import com.chyld.enums.Rating;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "movies")
@Data
public class Movie {
    private int id;
    private int version;
    private String name;
    private Date released;
    private Genre genre;
    private Rating rating;
    private Studio studio;
    private Date createdAt;
    private Date updatedAt;

    public Movie() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @Id
    @GeneratedValue
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    @Version
    public int getVersion() {return version;}
    public void setVersion(int version) {this.version = version;}

    @Column(nullable = false)
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Temporal(TemporalType.DATE)
    public Date getReleased() {return released;}
    public void setReleased(Date released) {this.released = released;}

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTION', 'SCIFI', 'HORROR')")
    public Genre getGenre() {return genre;}
    public void setGenre(Genre genre) {this.genre = genre;}

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('G', 'PG', 'R')")
    public Rating getRating() {return rating;}
    public void setRating(Rating rating) {this.rating = rating;}

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "studio_id")
    public Studio getStudio() {return studio;}
    public void setStudio(Studio studio) {this.studio = studio;}

    @Column(name = "created_at", nullable = false, updatable = false)
    public Date getCreatedAt() {return createdAt;}
    public void setCreatedAt(Date createdAt) {this.createdAt = createdAt;}

    @Column(name = "updated_at", nullable = false)
    public Date getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(Date updatedAt) {this.updatedAt = updatedAt;}

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}
