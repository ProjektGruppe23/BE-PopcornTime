package com.example.bepopcorntime.seat;

import com.example.bepopcorntime.booked_seat.BookedSeat;
import com.example.bepopcorntime.theatre.Theatre;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "theatre_id", nullable = false)
    private Theatre theatre;

    @OneToMany(mappedBy = "seat")
    private Set<BookedSeat> bookedSeats = new HashSet<>();
}
