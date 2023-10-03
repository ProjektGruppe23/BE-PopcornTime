package com.example.bepopcorntime.theatre;


import com.example.bepopcorntime.seat.Seat;
import com.example.bepopcorntime.showtime.Showtime;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Theatre
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "theatre")  // Changed from @OneToOne
    private List<Showtime> showtimes = new ArrayList<>();  // Changed from a single Showtime to a List

    @OneToMany(mappedBy = "theatre")
    private List<Seat> seats = new ArrayList<>();
}
