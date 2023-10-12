package com.example.bepopcorntime.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer>
{
    List<Movie> findByMovieGenres_Genre_Id(int genreid);

    @Query("SELECT new com.example.bepopcorntime.movie.Movie(movie.id, movie.title, movie.picture) FROM Movie movie")
    List<Movie> findIdAndTitleAndPicture(); //using the Query annotation to make a custom query to only get the collums from movie that is need instead of ALL of it.
}
