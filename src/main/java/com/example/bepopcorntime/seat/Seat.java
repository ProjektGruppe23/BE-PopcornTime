package com.example.bepopcorntime.seat;

import com.example.bepopcorntime.booked_seat.BookedSeat;
import com.example.bepopcorntime.theatre.Theatre;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference(value = "theatre-seat")
    private Theatre theatre;

    @OneToMany(mappedBy = "seat")
    @JsonManagedReference(value = "seat-bookedSeat")
    private Set<BookedSeat> bookedSeats = new HashSet<>();
}
