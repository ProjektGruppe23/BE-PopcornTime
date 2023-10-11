package com.example.bepopcorntime.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer>
{
    List<Movie> findByMovieGenres_Genre_Id(int genreid);

    List<Movie> findIdAndTitleAndPicture();
}
