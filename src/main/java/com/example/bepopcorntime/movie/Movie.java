package com.example.bepopcorntime.movie;

import com.example.bepopcorntime.age_limit.AgeLimit;
import com.example.bepopcorntime.movie_genre.MovieGenre;
import com.example.bepopcorntime.showtime.Showtime;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Movie
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private String picture;
    private int length;

    @ManyToOne
    @JoinColumn(name = "ageLimit", referencedColumnName = "id", nullable = false)
    AgeLimit ageLimit;

    @OneToMany(mappedBy = "movie")
    private Set<Showtime> showtimes = new HashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<MovieGenre> movieGenres = new HashSet<>();

    public Movie()
    {
    }

    public Movie(int id, String title, String picture) // Constructor for the custom Query for the movie search in the navbar
    {
        this.id = id;
        this.title = title;
        this.picture = picture;
    }
}
