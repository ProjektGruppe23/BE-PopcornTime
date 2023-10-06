package com.example.bepopcorntime.showtime;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {
    List<Showtime> findShowtimesByMovieId(int movieId);
}
