package com.example.bepopcorntime.showtime;

import com.example.bepopcorntime.booked_seat.BookedSeat;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ShowtimeRESTController {
    @Autowired
    ShowtimeRepository showtimeRepository;

    @GetMapping("/showtimes/{movieid}")
    public List<Date> getShowtimesByMovieId(@PathVariable int movieid, HttpSession session) {
        List<Showtime> showtimesByMovie = showtimeRepository.findShowtimesByMovieId(movieid);
        List<Date> time_starts = new ArrayList<>();
        for (Showtime showtime : showtimesByMovie) {
            time_starts.add(showtime.getTime_start());
        }

        session.setAttribute("showtimes", time_starts);
        return time_starts;
    }

    @GetMapping("oneshowtime/{showtimeId}")
    public ResponseEntity<Showtime> getOneShowtime(@PathVariable int showtimeId, HttpSession session)
    {
        Optional<Showtime> showtimeOpt = showtimeRepository.findShowtimeById(showtimeId);

        if (showtimeOpt.isPresent())
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
