package com.example.bepopcorntime.showtime;


import com.example.bepopcorntime.movie.Movie;
import com.example.bepopcorntime.theatre.Theatre;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class Showtime
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date timeStart;

    @ManyToOne  // Changed from @OneToOne
    @JoinColumn(name = "theatre_id") // Provide the actual column name in your database
    private Theatre theatre;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false)
    private Movie movie;
}
