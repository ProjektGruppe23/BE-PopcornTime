package com.example.bepopcorntime.genre;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer>
{
    List<Genre> findByMovieGenres_Movie_Id(int movieid);
}
