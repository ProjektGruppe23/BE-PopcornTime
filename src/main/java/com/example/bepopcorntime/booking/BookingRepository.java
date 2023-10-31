package com.example.bepopcorntime.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer>
{
    List<Booking> findIdByEmail(String email);

    int findByEmail(String email);
}
