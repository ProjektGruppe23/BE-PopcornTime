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
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Movie
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private Date startDate;
    private Date endDate;
    private String picture;
    private int length;

    @OneToOne
    @JoinColumn(name = "ageLimit", referencedColumnName = "id", nullable = false)
    AgeLimit ageLimit;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    @JsonBackReference
    private List<Showtime> showtimes = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private Set<MovieGenre> movieGenreSet = new HashSet<>();

}
