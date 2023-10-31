package com.example.bepopcorntime.booked_seat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookedSeatRepository extends JpaRepository<BookedSeat, Integer>
{
    List<BookedSeat> findBookedSeatByShowtime_Id(int showtimeData);
    // int findBookingIdByShowtimeId(int showtimeId);

    @Query("SELECT b.booking.id FROM BookedSeat b WHERE b.showtime.id = :showtimeId")
    List<Integer> findBookingIdByShowtimeId(@Param("showtimeId") int showtimeId);

    String findBySeat(String seat);


}
