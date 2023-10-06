package com.example.bepopcorntime.booking;

import com.example.bepopcorntime.booked_seat.BookedSeat;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "booking")
    @JsonBackReference //??
    private Set<BookedSeat> bookedSeats = new HashSet<>();
}
