package com.uber.BookingService.dtos;

import com.uber.BookingService.constant.BookingStatus;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UpdateBookingRequestDto {
    private Long bookingId;
    private Long driverId;
    private BookingStatus bookingStatus;
}
