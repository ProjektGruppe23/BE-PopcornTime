package com.example.bepopcorntime.theatre;


import com.example.bepopcorntime.seat.Seat;
import com.example.bepopcorntime.showtime.Showtime;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

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

    @OneToOne(mappedBy = "theatre")
    @JsonBackReference
    private Showtime showtime;

    @OneToMany(mappedBy = "theatre")
    private List<Seat> seats;

}
