package com.example.bepopcorntime.showtime;

import com.example.bepopcorntime.booked_seat.BookedSeat;
import com.example.bepopcorntime.movie.Movie;
import com.example.bepopcorntime.theatre.Theatre;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Showtime
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)  // Timestamp includes date and time down to the second second.
    private Date time_start;

    @ManyToOne // Changed from @OneToOne
    @JoinColumn(name = "theatre_id") // Provide the actual column name in your database
    private Theatre theatre;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false)
    private Movie movie;

    @OneToMany
    @JoinColumn(name = "showtime_id", referencedColumnName = "id")
    private Set<BookedSeat> bookedSeats = new HashSet<>();
}
