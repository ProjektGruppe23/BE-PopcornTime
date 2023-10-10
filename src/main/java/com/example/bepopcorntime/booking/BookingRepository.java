package com.example.bepopcorntime.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Integer>
{

    int findByEmail(String email);
}
