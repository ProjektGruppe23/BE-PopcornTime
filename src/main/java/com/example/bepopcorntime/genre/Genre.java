package com.example.bepopcorntime.genre;

import com.example.bepopcorntime.movie_genre.MovieGenre;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;

    @OneToMany(mappedBy = "genre")
    private Set<MovieGenre> movieGenres = new HashSet<>();
}
