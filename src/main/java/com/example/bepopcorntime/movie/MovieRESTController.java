package com.example.bepopcorntime.movie;


import com.example.bepopcorntime.age_limit.AgeLimit;
import com.example.bepopcorntime.age_limit.AgeLimitRepository;
import com.example.bepopcorntime.movie_genre.MovieGenreRepository;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class MovieRESTController
{
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieGenreRepository movieGenreRepository;

    @Autowired
    AgeLimitRepository ageLimitRepository;

    private static final Logger logger = LoggerFactory.getLogger(MovieRESTController.class);


    @GetMapping("/allmovies")
    //Should be renamed to something like "/search" since this no longer gets everything from the movie table
    public List<Movie> getAllMovies() //this should be renamed aswell
    {
        List<Movie> allMovies = movieRepository.findIdAndTitleAndPicture(); //The method that uses the custom JPA query annotation is called here to be sent to the frontend with only the collums needed.
        return allMovies;
    }

    @GetMapping("/movies")
    public List<Movie> getMovies()
    {
        List<Movie> movies = movieRepository.findAll();
        return movies;
    }

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
                movie.setAgeLimitForDisplay(movie.ageLimit.getAge());
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
                movie.setAgeLimitForDisplay(movie.ageLimit.getAge());
                selectedMovies.add(movie);
            }
        }

        return selectedMovies;
    }

    @GetMapping("/movies/{genreId}")
    public List<Movie> getMoviesByGenreId(@PathVariable int genreId, HttpSession session)
    {
        List<Movie> moviesByGenre = movieRepository.findByMovieGenres_Genre_Id(genreId);
        session.setAttribute("movies", moviesByGenre);
        return moviesByGenre;
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<Movie> getSelectedMovie(@PathVariable int movieId, HttpSession session)
    {
        Optional<Movie> movieOpt = movieRepository.findById(movieId);


        if ( movieOpt.isPresent() )
        {
            Movie movie = movieOpt.get();

            session.setAttribute("movieId", movie.getId());
            session.setAttribute("title", movie.getTitle());
            session.setAttribute("endDate", movie.getEndDate());
            session.setAttribute("startDate", movie.getStartDate());
            session.setAttribute("picture", movie.getPicture());
            session.setAttribute("ageLimit", movie.getAgeLimit());
            session.setAttribute("length", movie.getLength());


            return ResponseEntity.ok(movie);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/movie")
    public Movie createMovie(@RequestBody Movie movie)
    {
        logger.info("Received movie data for creation: {}", movie); // Debugging line

        // Fetch the existing AgeLimit from the database using the id provided
        Optional<AgeLimit> existingAgeLimit = ageLimitRepository.findById(movie.getAgeLimit().getId());

        if (existingAgeLimit.isPresent())
        {
            // If found, set the complete AgeLimit object to movie
            movie.setAgeLimit(existingAgeLimit.get());
        }
        else
        {
            // Handle error by throwing an exception
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "AgeLimit with the given ID not found");
        }
        return movieRepository.save(movie);
    }

    @PutMapping("/movie/{id}")
    public Movie updateMovie(@PathVariable int id, @RequestBody Movie movie)
    {
        logger.info("Received movie data for update: {}", movie); // Debugging line
        movie.setId(id);
        return movieRepository.save(movie);
    }
}
