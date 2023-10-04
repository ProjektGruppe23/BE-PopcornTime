package com.example.bepopcorntime.booked_seat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookedSeatRepository extends JpaRepository<BookedSeat, Integer>
{
    List<BookedSeat> findBookedSeatByShowtime_Id(int showtimeData);
}
