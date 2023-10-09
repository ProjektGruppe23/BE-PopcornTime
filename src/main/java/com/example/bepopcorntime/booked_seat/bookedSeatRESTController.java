package com.example.bepopcorntime.booked_seat;

import com.example.bepopcorntime.movie.Movie;
import com.example.bepopcorntime.movie.MovieRESTController;
import com.example.bepopcorntime.showtime.Showtime;
import com.example.bepopcorntime.showtime.ShowtimeRepository;
import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin
public class bookedSeatRESTController
{

    @Autowired
    BookedSeatRepository bookedSeatRepository;

    @Autowired
    MovieRESTController movieRESTController;

    @Autowired
    ShowtimeRepository showtimeRepository;


    @GetMapping("/getBookedSeats/{showtime_Id}")
    public ResponseEntity<List<BookedSeat>> getBookedSeats(@PathVariable int showtime_Id, HttpSession session)
    {
        Optional<Showtime> showtimeOpt = showtimeRepository.findShowtimeById(showtime_Id);
        
        if(showtimeOpt.isPresent())
        {
            Showtime showtime = showtimeOpt.get();
            session.setAttribute("movieId", showtime.getMovie().getId());
            session.setAttribute("showtimeId", showtime.getId());
            session.setAttribute("time_start", showtime.getTime_start());
            
        }
        
        List<BookedSeat> listOfBookedSeats = bookedSeatRepository.findBookedSeatByShowtime_Id(showtime_Id);
        return new ResponseEntity<>(listOfBookedSeats, HttpStatus.OK);
    }


    /*@GetMapping("/getMovieDetails")
    public ResponseEntity<List<Movie>>  getMovieDetails(HttpSession session)
    {
        Movie movie = new Movie();
        movie.setTitle("Halløj fra badehotellet");
        movie.setPicture("https://image.tmdb.org/t/p/w500/xtQQ839N83ThumSLn2NiFp9O70C.jpg");
        movie.setLength(177);
        movie.setStartDate(new java.sql.Date(2021, 10, 10));
        movie.setEndDate(new java.sql.Date(2022, 10, 10));
        List<Movie> listOfMovieDetails = new ArrayList<>();
        listOfMovieDetails.add(movie);
        return new ResponseEntity<>(listOfMovieDetails, HttpStatus.OK);
    }*/


}