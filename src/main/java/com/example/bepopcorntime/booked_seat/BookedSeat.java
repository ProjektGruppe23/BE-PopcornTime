package com.example.bepopcorntime.booked_seat;

import com.example.bepopcorntime.booking.Booking;
import com.example.bepopcorntime.showtime.Showtime;
import com.example.bepopcorntime.theatre.Theatre;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference(value = "booking-bookedSeat")
    private Booking booking;

    private String seat;

    @ManyToOne
    @JoinColumn(name = "showtime_id", nullable = false)
    @JsonBackReference(value = "showtime-bookedSeat")
    private Showtime showtime;

    /*@ManyToOne
    @JoinColumn(name = "theatre_id", nullable = false)
    @JsonBackReference(value = "theatre-bookedSeat")
    private Theatre theatre;*/
}
