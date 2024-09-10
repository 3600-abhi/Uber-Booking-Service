package com.uber.BookingService.dtos;

import com.uber.BookingService.models.ExactLocation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateBookingRequestDto {
    private Long passengerId;
    private ExactLocation startLocation;
    private ExactLocation endLocation;
}
