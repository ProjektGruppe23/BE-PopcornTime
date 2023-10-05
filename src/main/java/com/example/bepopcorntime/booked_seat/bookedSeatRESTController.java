package com.example.bepopcorntime.booked_seat;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class bookedSeatRESTController
{

    @Autowired
    BookedSeatRepository bookedSeatRepository;



    @GetMapping("/getBookedSeats")
    public ResponseEntity<List<BookedSeat>> getBookedSeats(Session session)
    {
        List<BookedSeat> listOfBookedSeats = bookedSeatRepository.findBookedSeatByShowtime_Id(1);
        return new ResponseEntity<>(listOfBookedSeats, HttpStatus.OK);
    }
}