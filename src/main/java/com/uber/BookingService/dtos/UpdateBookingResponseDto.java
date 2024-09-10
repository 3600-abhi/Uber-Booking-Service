package com.uber.BookingService.dtos;

import com.uber.BookingService.constant.BookingStatus;
import com.uber.BookingService.models.Driver;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateBookingResponseDto {
    private Long bookingId;
    private BookingStatus bookingStatus;
    private Long driverId;
}
