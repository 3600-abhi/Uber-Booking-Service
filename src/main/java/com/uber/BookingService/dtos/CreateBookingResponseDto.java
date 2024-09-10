package com.uber.BookingService.dtos;

import com.uber.BookingService.models.Driver;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Data
@Builder
public class CreateBookingResponseDto {
    private Long bookingId;
    private String bookingStatus;
    private Optional<Driver> driver;
}
