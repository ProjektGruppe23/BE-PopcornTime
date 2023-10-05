package com.example.bepopcorntime.movie;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class MovieRESTController
{
    @Autowired
    MovieRepository movieRepository;

    /*@GetMapping("/movies")
    public List<Movie> getAllMovies()
    {
        return movieRepository.findAll();
    }*/

    @GetMapping("/movies/current")
    public List<Movie> getCurrentMovies()
    {
        Date currentDate = Date.valueOf(java.time.LocalDate.now());
        List<Movie> allMovies = movieRepository.findAll();
        List<Movie> selectedMovies = new ArrayList<>();
        for ( Movie movie : allMovies )
        {
            if ( movie.getStartDate().compareTo(currentDate) <= 0 )
            {
                selectedMovies.add(movie);
            }
        }

        return selectedMovies;
    }

    @GetMapping("/movies/upcoming")
    public List<Movie> getUpcomingMovies()
    {
        Date currentDate = Date.valueOf(java.time.LocalDate.now());
        List<Movie> allMovies = movieRepository.findAll();
        List<Movie> selectedMovies = new ArrayList<>();
        for ( Movie movie : allMovies )
        {
            if ( movie.getStartDate().compareTo(currentDate) > 0 )
            {
                selectedMovies.add(movie);
            }
        }

        return selectedMovies;
    }

    @GetMapping("/movies/byGenre/{genreId}")
    public List<Movie> getMoviesByGenre(@PathVariable int genreId)
    {
        List<Movie> moviesByGenre = movieRepository.findByMovieGenres_Genre_Id(genreId);
        return moviesByGenre;
    }


}
