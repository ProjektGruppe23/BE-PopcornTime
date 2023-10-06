package com.example.bepopcorntime.booked_seat;

import com.example.bepopcorntime.movie.Movie;
import com.example.bepopcorntime.movie.MovieRESTController;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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


    @GetMapping("/getBookedSeats")
    public ResponseEntity<List<BookedSeat>> getBookedSeats(Session session)
    {
        List<BookedSeat> listOfBookedSeats = bookedSeatRepository.findBookedSeatByShowtime_Id(1); //session.get(showTimeId);
        return new ResponseEntity<>(listOfBookedSeats, HttpStatus.OK);
    }


    @GetMapping("/getMovieDetails")
    public ResponseEntity<List<Movie>>  getMovieDetails(Session session)
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
    }


}