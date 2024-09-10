package com.uber.BookingService.repositories;

import com.uber.BookingService.constant.BookingStatus;
import com.uber.BookingService.models.Booking;
import com.uber.BookingService.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Booking b SET b.bookingStatus = :bookingStatus, b.driver = :driver WHERE b.id = :id")
    void updateBookingStatusAndDriverById(@Param("id") Long id, @Param("bookingStatus") BookingStatus bookingStatus, @Param("driver") Driver driver);
}
