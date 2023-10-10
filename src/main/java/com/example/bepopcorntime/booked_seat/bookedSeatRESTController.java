package com.example.bepopcorntime.booked_seat;

import com.example.bepopcorntime.booking.Booking;
import com.example.bepopcorntime.booking.BookingRepository;
import com.example.bepopcorntime.movie.Movie;
import com.example.bepopcorntime.movie.MovieRESTController;
import com.example.bepopcorntime.seat.Seat;
import com.example.bepopcorntime.seat.SeatRepository;
import com.example.bepopcorntime.showtime.Showtime;
import com.example.bepopcorntime.showtime.ShowtimeRepository;
import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    BookingRepository bookingRepository;

   @Autowired
    SeatRepository seatRepository;


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

    @PostMapping("/getBookedSeats")
    public ResponseEntity<BookedSeat> postShowtimeIdAndSeatIdAndBookingId(@RequestParam(name = "email") String email,
                                                                          @RequestParam(name = "arrayParam") List<Integer> seatIds,
                                                                          @RequestParam(name = "intParam") int showtimeId)
    {
        Booking booking = new Booking();
        booking.setEmail(email);
        bookingRepository.save(booking);

        BookedSeat savedBookedSeat = new BookedSeat();

        int bookingId = booking.getId(); // Retrieve the booking ID after saving

        Showtime showtime = showtimeRepository.findById(showtimeId).orElse(null);



        if (showtimeId == 0)
        {
            System.out.println("showtime if");
            // Handle the case where the showtime with the given ID does not exist.
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        for (Integer seatId : seatIds)
        {
            Seat seat = seatRepository.findById(seatId).orElse(null);

            if (seatId == 0)
            {
                System.out.println("seat if");
                // Handle the case where the seat with the given ID does not exist.
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            BookedSeat bookedSeat = new BookedSeat();
            bookedSeat.setBooking(booking);
            bookedSeat.setSeat(seat);
            bookedSeat.setShowtime(showtime);
            savedBookedSeat = bookedSeatRepository.save(bookedSeat);


        }

        return new ResponseEntity<>(savedBookedSeat, HttpStatus.CREATED);
    }

}

