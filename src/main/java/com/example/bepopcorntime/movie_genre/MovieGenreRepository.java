package com.example.bepopcorntime.movie_genre;

import com.example.bepopcorntime.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface MovieGenreRepository extends JpaRepository<MovieGenre, Integer>
{
}
