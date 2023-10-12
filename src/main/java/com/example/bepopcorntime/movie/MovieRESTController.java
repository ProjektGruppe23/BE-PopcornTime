package com.example.bepopcorntime.movie;


import com.example.bepopcorntime.age_limit.AgeLimit;
import com.example.bepopcorntime.age_limit.AgeLimitRepository;
import com.example.bepopcorntime.movie_genre.MovieGenreRepository;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/allmovies")
    public List<Movie> getAllMovies()
    {
        List<Movie> allMovies = movieRepository.findAll();
        return allMovies;
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
    public Movie createMovie(@RequestBody Movie movie) throws Exception {  // Added Exception for demo
        int ageLimitId = movie.getAgeLimit().getId(); // assuming you're sending AgeLimit ID from client-side
        AgeLimit existingAgeLimit = ageLimitRepository.findById(ageLimitId)
                .orElseThrow(() -> new Exception("AgeLimit not found")); // replace Exception with your custom one
        movie.setAgeLimit(existingAgeLimit);

        return movieRepository.save(movie);
    }

    @PutMapping("/movie/{id}")
    public Movie updateMovie(@PathVariable int id, @RequestBody Movie movie)
    {
        movie.setId(id);
        return movieRepository.save(movie);
    }

    @DeleteMapping("/movie/{id}")
    public void deleteMovie(@PathVariable int id)
    {
        movieRepository.deleteById(id);
    }
}
