package com.example.bepopcorntime.showtime;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {
    List<Showtime> findShowtimesByMovieId(int movieId);
    //List<Showtime> findShowtimeByMovieIdAndTimeStart(int movieId, Date time_start);
    
    Optional<Showtime> findShowtimeById(int id);
}
