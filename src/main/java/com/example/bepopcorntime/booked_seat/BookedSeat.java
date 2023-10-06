package com.example.bepopcorntime.booked_seat;

import com.example.bepopcorntime.booking.Booking;
import com.example.bepopcorntime.seat.Seat;
import com.example.bepopcorntime.showtime.Showtime;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BookedSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "showtime_id", nullable = false)
    private Showtime showtime;
}
