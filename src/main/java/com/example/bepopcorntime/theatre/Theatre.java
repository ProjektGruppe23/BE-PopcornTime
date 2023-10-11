package com.example.bepopcorntime.theatre;


import com.example.bepopcorntime.booked_seat.BookedSeat;
import com.example.bepopcorntime.showtime.Showtime;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "theatre")  // Changed from @OneToOne
    @JsonManagedReference(value = "theatre-showtime")
    private Set<Showtime> showtimes = new HashSet<>();

    /*@OneToMany(mappedBy = "theatre")
    @JsonManagedReference(value = "theatre-bookedSeat")
    private Set<BookedSeat> seats = new HashSet<>();*/
}
