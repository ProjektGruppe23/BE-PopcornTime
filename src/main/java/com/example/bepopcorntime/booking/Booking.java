package com.example.bepopcorntime.booking;

import com.example.bepopcorntime.booked_seat.BookedSeat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Booking
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "booking")
    private List<BookedSeat> bookedSeats;
}
