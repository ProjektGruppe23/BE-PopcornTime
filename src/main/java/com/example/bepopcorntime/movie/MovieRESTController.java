package com.example.bepopcorntime.movie;


import com.example.bepopcorntime.movie_genre.MovieGenreRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@CrossOrigin
public class MovieRESTController {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieGenreRepository movieGenreRepository;

    @GetMapping("/selectedmovie/{movieid}")
    public ResponseEntity<Movie> getSelectedMovie(@PathVariable int movieid, HttpSession session) {
        Optional<Movie> movieOpt = movieRepository.findById(movieid);

        if (movieOpt.isPresent()) {
            Movie movie = movieOpt.get();

            session.setAttribute("movieId", movie.getId());
            session.setAttribute("title", movie.getTitle());
            session.setAttribute("endDate", movie.getEndDate());
            session.setAttribute("startDate", movie.getStartDate());
            session.setAttribute("picture", movie.getPicture());
            session.setAttribute("ageLimit", movie.getAgeLimit());
            session.setAttribute("length", movie.getLength());


            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
