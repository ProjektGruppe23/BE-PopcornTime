package com.example.bepopcorntime.showtime;


import com.example.bepopcorntime.movie.Movie;
import com.example.bepopcorntime.seat.Seat;
import com.example.bepopcorntime.theatre.Theatre;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Entity
@Data
public class Showtime
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date timeStart;

    @OneToOne(mappedBy = "theatre")
    private Theatre theatre;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false)
    private Movie movie;
}
