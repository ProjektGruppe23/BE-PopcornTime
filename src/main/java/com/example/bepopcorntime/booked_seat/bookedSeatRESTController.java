package com.example.bepopcorntime.booked_seat;

import com.example.bepopcorntime.booking.Booking;
import com.example.bepopcorntime.booking.BookingRepository;
import com.example.bepopcorntime.movie.MovieRESTController;
import com.example.bepopcorntime.showtime.Showtime;
import com.example.bepopcorntime.showtime.ShowtimeRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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


    @GetMapping("/getBookedSeats/{showtime_Id}")
    public ResponseEntity<List<BookedSeat>> getBookedSeats(@PathVariable int showtime_Id, HttpSession session)
    {
        Optional<Showtime> showtimeOpt = showtimeRepository.findShowtimeById(showtime_Id);

        if ( showtimeOpt.isPresent() )
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
                                                                          @RequestParam(name = "arrayParam") List<String> seatIds,
                                                                          @RequestParam(name = "intParam") int showtimeId)
    {
        Booking booking = new Booking();
        booking.setEmail(email);
        bookingRepository.save(booking);

        BookedSeat savedBookedSeat = new BookedSeat();

        int bookingId = booking.getId(); // Retrieve the booking ID after saving

        Showtime showtime = showtimeRepository.findById(showtimeId).orElse(null);


        if ( showtimeId == 0 )
        {
            System.out.println("showtime if");
            // Handle the case where the showtime with the given ID does not exist.
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        for ( String seatId : seatIds )
        {
            //String seat = bookedSeatRepository.findBySeat(seatId);

            if ( seatId == "" )
            {
                System.out.println("seat if");
                // Handle the case where the seat with the given ID does not exist.
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            BookedSeat bookedSeat = new BookedSeat();
            bookedSeat.setBooking(booking);
            bookedSeat.setSeat(seatId);
            bookedSeat.setShowtime(showtime);
            savedBookedSeat = bookedSeatRepository.save(bookedSeat);


        }

        return new ResponseEntity<>(savedBookedSeat, HttpStatus.CREATED);
    }

    @GetMapping("/bookingConfirmed")
    public int getBookingId(@RequestParam(name = "email") String email, @RequestParam(name = "intParam") int showtimeId) throws Exception
    {
        List<Booking> allBookingIdsByEmail = bookingRepository.findIdByEmail(email);

        int foundBookingId = -99;

        for ( Booking bookingId : allBookingIdsByEmail )
        {
            List<Integer> bookingIdForShowtime = bookedSeatRepository.findBookingIdByShowtimeId(showtimeId);

            for ( int i = 0; i < bookingIdForShowtime.size(); i++ )
            {
                if ( bookingIdForShowtime.get(i) == bookingId.getId() )
                {
                    foundBookingId = bookingId.getId();
                    break;
                }

            }
        }


        if ( foundBookingId == -99 )
        {
            throw new Exception("Booking ID not found");
        }

        return foundBookingId;
    }

}

