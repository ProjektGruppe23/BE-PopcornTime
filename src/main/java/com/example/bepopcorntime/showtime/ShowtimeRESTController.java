package com.example.bepopcorntime.showtime;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ShowtimeRESTController {
    @Autowired
    ShowtimeRepository showtimeRepository;

    @GetMapping("/showtimes/{movieid}")
    public List<String> getShowtimesByMovieId(@PathVariable int movieid, HttpSession session) {
        List<Showtime> showtimesByMovie = showtimeRepository.findShowtimesByMovieId(movieid);
        List<String> timeStarts = new ArrayList<>();
        for (Showtime showtime : showtimesByMovie) {
            timeStarts.add(showtime.getTimeStart());
        }

        session.setAttribute("showtimes", timeStarts);
        return timeStarts;
    }
}
