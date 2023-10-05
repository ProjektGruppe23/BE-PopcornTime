package com.example.bepopcorntime.movie;

import com.example.bepopcorntime.age_limit.AgeLimit;
import com.example.bepopcorntime.genre.Genre;
import com.example.bepopcorntime.movie_genre.MovieGenre;
import com.example.bepopcorntime.showtime.Showtime;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Movie
{
    @ManyToOne
    @JoinColumn(name = "ageLimit", referencedColumnName = "id", nullable = false)
    AgeLimit ageLimit;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private Date startDate;
    private Date endDate;
    private String picture;
    private int length;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    //@JsonBackReference
    private Set<Showtime> showtimes = new HashSet<>();

    @OneToMany(mappedBy = "movie")
    //@JsonBackReference
    private Set<MovieGenre> movieGenres = new HashSet<>();


}
