package com.example.bepopcorntime.theatre;


import com.example.bepopcorntime.seat.Seat;
import com.example.bepopcorntime.showtime.Showtime;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

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
    private Set<Showtime> showtimes = new HashSet<>();

    @OneToMany(mappedBy = "theatre")
    private Set<Seat> seats = new HashSet<>();
}
