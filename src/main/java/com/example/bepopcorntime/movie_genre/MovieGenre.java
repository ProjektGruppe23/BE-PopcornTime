package com.example.bepopcorntime.movie_genre;

import com.example.bepopcorntime.genre.Genre;
import com.example.bepopcorntime.movie.Movie;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MovieGenre
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;
}
