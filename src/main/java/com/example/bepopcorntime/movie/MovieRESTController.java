package com.example.bepopcorntime.movie;


import com.example.bepopcorntime.movie_genre.MovieGenre;
import com.example.bepopcorntime.movie_genre.MovieGenreRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/selectedmovie/{id}")
    public ResponseEntity<Movie> getSelectedMovie(@PathVariable int id, HttpSession session)
    {
        Optional<Movie> movieOpt = movieRepository.findById(id);



        if(movieOpt.isPresent())
        {
            Movie movie = movieOpt.get();

            /*List<String> genreNames = new ArrayList<>();
            for (MovieGenre movieGenre : movie.getMovieGenres()) {
                genreNames.add(movieGenre.getGenre().getType());
            }*/

            session.setAttribute("movieId", movie.getId());
            session.setAttribute("title", movie.getTitle());
            session.setAttribute("endDate", movie.getEndDate());
            session.setAttribute("startDate", movie.getStartDate());
            session.setAttribute("picture", movie.getPicture());
            session.setAttribute("ageLimit", movie.getAgeLimit());
            session.setAttribute("length", movie.getLength());
            session.setAttribute("genre", movie.getMovieGenres());


            return ResponseEntity.ok(movie);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
}
