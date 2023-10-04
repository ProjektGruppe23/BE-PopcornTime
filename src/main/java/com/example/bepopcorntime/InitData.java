/*package com.example.bepopcorntime;

import com.example.bepopcorntime.age_limit.AgeLimit;
import com.example.bepopcorntime.age_limit.AgeLimitRepository;
import com.example.bepopcorntime.booked_seat.BookedSeat;
import com.example.bepopcorntime.booked_seat.BookedSeatRepository;
import com.example.bepopcorntime.booking.Booking;
import com.example.bepopcorntime.booking.BookingRepository;
import com.example.bepopcorntime.genre.Genre;
import com.example.bepopcorntime.genre.GenreRepository;
import com.example.bepopcorntime.movie.Movie;
import com.example.bepopcorntime.movie.MovieRepository;
import com.example.bepopcorntime.movie_genre.MovieGenre;
import com.example.bepopcorntime.movie_genre.MovieGenreRepository;
import com.example.bepopcorntime.seat.Seat;
import com.example.bepopcorntime.seat.SeatRepository;
import com.example.bepopcorntime.showtime.Showtime;
import com.example.bepopcorntime.showtime.ShowtimeRepository;
import com.example.bepopcorntime.theatre.Theatre;
import com.example.bepopcorntime.theatre.TheatreRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class InitData
{
    @Autowired
    private AgeLimitRepository ageLimitRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookedSeatRepository bookedSeatRepository;

    @Autowired
    private MovieGenreRepository movieGenreRepository;

    @PostConstruct
    public void init()
    {
        // 1. Initialize Age Limits
        AgeLimit ageLimitPG13 = new AgeLimit();
        ageLimitPG13.setAge(13);
        ageLimitRepository.save(ageLimitPG13);

        AgeLimit ageLimitR = new AgeLimit();
        ageLimitR.setAge(18);
        ageLimitRepository.save(ageLimitR);

        // 2. Initialize Genres
        Genre action = new Genre();
        action.setType("Action");
        genreRepository.save(action);

        Genre comedy = new Genre();
        comedy.setType("Comedy");
        genreRepository.save(comedy);

        // 3. Initialize Movies
        // Upcoming Movies
        Movie upcomingMovie1 = new Movie();
        upcomingMovie1.setTitle("Upcoming Movie 1");
        upcomingMovie1.setStartDate(Date.valueOf("2023-12-01"));
        upcomingMovie1.setEndDate(Date.valueOf("2024-01-01"));
        upcomingMovie1.setPicture("upcoming1.jpg");
        upcomingMovie1.setLength(120);
        upcomingMovie1.setAgeLimit(ageLimitPG13);
        movieRepository.save(upcomingMovie1);

        Movie upcomingMovie2 = new Movie();
        upcomingMovie2.setTitle("Upcoming Movie 2");
        upcomingMovie2.setStartDate(Date.valueOf("2023-12-15"));
        upcomingMovie2.setEndDate(Date.valueOf("2024-01-15"));
        upcomingMovie2.setPicture("upcoming2.jpg");
        upcomingMovie2.setLength(110);
        upcomingMovie2.setAgeLimit(ageLimitPG13);
        movieRepository.save(upcomingMovie2);

        Movie upcomingMovie3 = new Movie();
        upcomingMovie3.setTitle("Upcoming Movie 3");
        upcomingMovie3.setStartDate(Date.valueOf("2024-01-01"));
        upcomingMovie3.setEndDate(Date.valueOf("2024-02-01"));
        upcomingMovie3.setPicture("upcoming3.jpg");
        upcomingMovie3.setLength(130);
        upcomingMovie3.setAgeLimit(ageLimitR);
        movieRepository.save(upcomingMovie3);

        // Now Playing Movies
        Movie nowPlayingMovie1 = new Movie();
        nowPlayingMovie1.setTitle("Now Playing Movie 1");
        nowPlayingMovie1.setStartDate(Date.valueOf("2023-09-01"));
        nowPlayingMovie1.setEndDate(Date.valueOf("2023-10-01"));
        nowPlayingMovie1.setPicture("nowPlaying1.jpg");
        nowPlayingMovie1.setLength(100);
        nowPlayingMovie1.setAgeLimit(ageLimitR);
        movieRepository.save(nowPlayingMovie1);

        Movie nowPlayingMovie2 = new Movie();
        nowPlayingMovie2.setTitle("Now Playing Movie 2");
        nowPlayingMovie2.setStartDate(Date.valueOf("2023-08-15"));
        nowPlayingMovie2.setEndDate(Date.valueOf("2023-09-15"));
        nowPlayingMovie2.setPicture("nowPlaying2.jpg");
        nowPlayingMovie2.setLength(90);
        nowPlayingMovie2.setAgeLimit(ageLimitPG13);
        movieRepository.save(nowPlayingMovie2);

        Movie nowPlayingMovie3 = new Movie();
        nowPlayingMovie3.setTitle("Now Playing Movie 3");
        nowPlayingMovie3.setStartDate(Date.valueOf("2023-09-10"));
        nowPlayingMovie3.setEndDate(Date.valueOf("2023-10-10"));
        nowPlayingMovie3.setPicture("nowPlaying3.jpg");
        nowPlayingMovie3.setLength(115);
        nowPlayingMovie3.setAgeLimit(ageLimitR);
        movieRepository.save(nowPlayingMovie3);

        // Initialize Movie Genres
        MovieGenre movieGenre1 = new MovieGenre();
        movieGenre1.setMovie(upcomingMovie1);
        movieGenre1.setGenre(action);
        movieGenreRepository.save(movieGenre1);

        MovieGenre movieGenre2 = new MovieGenre();
        movieGenre2.setMovie(upcomingMovie1);
        movieGenre2.setGenre(comedy);
        movieGenreRepository.save(movieGenre2);

        MovieGenre movieGenre3 = new MovieGenre();
        movieGenre3.setMovie(upcomingMovie2);
        movieGenre3.setGenre(action);
        movieGenreRepository.save(movieGenre3);

        // 4. Initialize Theatres
        Theatre theatre1 = new Theatre();
        theatre1.setName("Theatre 1");
        theatreRepository.save(theatre1);

        Theatre theatre2 = new Theatre();
        theatre2.setName("Theatre 2");
        theatreRepository.save(theatre2);

        // 6. Initialize Showtimes
        Showtime showtime1 = new Showtime();
        showtime1.setTimeStart(Date.valueOf("2023-10-10"));
        showtime1.setTheatre(theatre1);
        showtime1.setMovie(nowPlayingMovie1);
        showtimeRepository.save(showtime1);

        Showtime showtime2 = new Showtime();
        showtime2.setTimeStart(Date.valueOf("2023-10-11"));
        showtime2.setTheatre(theatre1);
        showtime2.setMovie(nowPlayingMovie2);
        showtimeRepository.save(showtime2);

        Showtime showtime3 = new Showtime();
        showtime3.setTimeStart(Date.valueOf("2023-10-12"));
        showtime3.setTheatre(theatre2);
        showtime3.setMovie(nowPlayingMovie3);
        showtimeRepository.save(showtime3);

        // 7. Initialize Bookings
        Booking booking1 = new Booking();
        booking1.setEmail("email1@example.com");
        bookingRepository.save(booking1);

        Booking booking2 = new Booking();
        booking2.setEmail("email2@example.com");
        bookingRepository.save(booking2);

        // 5. Initialize Seats
        Seat seat1 = new Seat();
        seat1.setTheatre(theatre1);
        seatRepository.save(seat1);

        Seat seat2 = new Seat();
        seat2.setTheatre(theatre1);
        seatRepository.save(seat2);

        Seat seat3 = new Seat();
        seat3.setTheatre(theatre2);
        seatRepository.save(seat3);

        Seat seat4 = new Seat();
        seat4.setTheatre(theatre2);
        seatRepository.save(seat4);

        // 8. Initialize BookedSeats
        BookedSeat bookedSeat1 = new BookedSeat();
        bookedSeat1.setBooking(booking1);
        bookedSeat1.setSeat(seat1);
        bookedSeat1.setShowtime(showtime1);
        bookedSeatRepository.save(bookedSeat1);


        BookedSeat bookedSeat2 = new BookedSeat();
        bookedSeat2.setBooking(booking2);
        bookedSeat2.setSeat(seat2);
        bookedSeat2.setShowtime(showtime2);
        bookedSeatRepository.save(bookedSeat2);

        //SPrint1
        // Sprint1 test 2
    }
}

 */