package com.example.bepopcorntime.movie;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
public class MovieRESTController
{
    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/selectedmovie/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id)
    {
        Optional<Movie> movieOpt = movieRepository.findById(id);
        if(movieOpt.isPresent())
        {
            Movie movie = movieOpt.get();
            return ResponseEntity.ok(movie);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
}
