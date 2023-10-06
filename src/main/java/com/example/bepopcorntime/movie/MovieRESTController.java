package com.example.bepopcorntime.movie;


import com.example.bepopcorntime.genre.Genre;
import com.example.bepopcorntime.genre.GenreRepository;
import com.example.bepopcorntime.movie_genre.MovieGenre;
import com.example.bepopcorntime.movie_genre.MovieGenreRepository;
import com.example.bepopcorntime.showtime.Showtime;
import com.example.bepopcorntime.showtime.ShowtimeRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.sql.Date;


@RestController
@CrossOrigin
public class MovieRESTController
{
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieGenreRepository movieGenreRepository;

    @Autowired
    GenreRepository genreRepository;
    @Autowired
    ShowtimeRepository showtimeRepository;

    @GetMapping("/selectedmovie/{movieid}")
    public ResponseEntity<Movie> getSelectedMovie(@PathVariable int movieid, HttpSession session)
    {
        Optional<Movie> movieOpt = movieRepository.findById(movieid);

        if(movieOpt.isPresent())
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

    @GetMapping("/genres/{movieId}")
    public List<String> getGenresByMovieId(@PathVariable int movieId, HttpSession session)
    {
        List<Genre> genresByMovieId = genreRepository.findByMovieGenres_Movie_Id(movieId);
        List<String> genres = new ArrayList<>();

        for (Genre genre : genresByMovieId)
        {
            genres.add(genre.getType());
        }


        session.setAttribute("genres", genres);
        return genres;
    }

    @GetMapping("/showtimes/{movieid}")
    public List<Date> getShowtimesByMovieId(@PathVariable int movieid, HttpSession session)
    {
        List<Showtime> showtimesByMovie = showtimeRepository.findShowtimesByMovieId(movieid);
        List<Date> timeStarts = new ArrayList<>();
        for(Showtime showtime : showtimesByMovie) {
            timeStarts.add(showtime.getTimeStart());
        }

        session.setAttribute("showtimes", timeStarts);
        return timeStarts;
    }
}
