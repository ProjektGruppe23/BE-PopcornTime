package com.example.bepopcorntime.showtime;


import com.example.bepopcorntime.booked_seat.BookedSeat;
import com.example.bepopcorntime.movie.Movie;
import com.example.bepopcorntime.theatre.Theatre;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)  // Timestamp includes date and time down to the second second.
    private Date time_start;

    @ManyToOne  // Changed from @OneToOne
    @JoinColumn(name = "theatre_id") // Provide the actual column name in your database
    @JsonBackReference(value = "theatre-showtime")
    private Theatre theatre;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference(value = "movie-showtime")
    private Movie movie;

    @OneToMany
    @JoinColumn(name = "showtime_id", referencedColumnName = "id")
    @JsonManagedReference(value = "showtime-bookedSeat")
    private Set<BookedSeat> bookedSeats = new HashSet<>();
}
