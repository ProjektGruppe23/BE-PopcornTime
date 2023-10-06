package com.example.bepopcorntime.movie;


import com.example.bepopcorntime.movie_genre.MovieGenreRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
