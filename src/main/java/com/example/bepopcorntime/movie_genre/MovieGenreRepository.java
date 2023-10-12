package com.example.bepopcorntime.movie_genre;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieGenreRepository extends JpaRepository<MovieGenre, Integer>
{
    Optional<MovieGenre> deleteByMovieId(int id);
}
