package com.example.bepopcorntime.showtime;

import com.example.bepopcorntime.movie.Movie;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ShowtimeRESTController
{
    @Autowired
    ShowtimeRepository showtimeRepository;

    /*@GetMapping("/showtimes/{movieid}")
    public List<Date> getShowtimesByMovieId(@PathVariable int movieid, HttpSession session) {
        List<Showtime> showtimesByMovie = showtimeRepository.findShowtimesByMovieId(movieid);
        List<Date> time_starts = new ArrayList<>();
        for (Showtime showtime : showtimesByMovie) {
            time_starts.add(showtime.getTime_start());
        }

        session.setAttribute("showtimes", time_starts);
        return time_starts;
    }*/

    /*@GetMapping("/showtimes/{movieid}")
    public List<Showtime> getShowtimesByMovieId(@PathVariable int movieid, HttpSession session) {
        List<Showtime> showtimesByMovie = showtimeRepository.findShowtimesByMovieId(movieid);
        List<Showtime> showtimes = new ArrayList<>();

        for (Showtime showtime : showtimesByMovie) {
            Showtime showtime1 = new Showtime();
            showtime1.setId(showtime.getId()); // Assuming getId() returns the ID
            showtime1.setTime_start(showtime.getTime_start()); // Assuming getTime_start() returns the Date
            showtimes.add(showtime1);
        }

        session.setAttribute("showtimes", showtimes);
        return showtimes;
    }*/

    @GetMapping("/showtimes/{movieid}")
    public List<Showtime> getShowtimesByMovieId(@PathVariable int movieid, HttpSession session)
    {
        List<Showtime> showtimesByMovie = showtimeRepository.findShowtimesByMovieId(movieid);
        List<Showtime> showtimes = new ArrayList<>();

        for ( Showtime showtime : showtimesByMovie )
        {
            // Assuming showtime already contains a reference to the associated Movie
            Movie movie = showtime.getMovie();

            // You can access the movieId from the associated Movie object
            int movieId = movie.getId();

            showtimes.add(showtime);
        }

        session.setAttribute("showtimes", showtimes);
        return showtimes;
    }

    @GetMapping("/oneshowtime/{showtimeId}")
    public ResponseEntity<Showtime> getOneShowtime(@PathVariable int showtimeId, HttpSession session)
    {
        Optional<Showtime> showtimeOpt = showtimeRepository.findShowtimeById(showtimeId);

        if ( showtimeOpt.isPresent() )
        {
            Showtime showtime = showtimeOpt.get();
            session.setAttribute("showtimeId", showtime.getId());
            return ResponseEntity.ok(showtime);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

}
