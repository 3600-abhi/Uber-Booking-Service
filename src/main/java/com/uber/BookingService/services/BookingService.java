package com.uber.BookingService.services;

import com.uber.BookingService.dtos.CreateBookingRequestDto;
import com.uber.BookingService.dtos.CreateBookingResponseDto;
import com.uber.BookingService.dtos.UpdateBookingRequestDto;
import com.uber.BookingService.dtos.UpdateBookingResponseDto;

public interface BookingService {
    public CreateBookingResponseDto createBooking(CreateBookingRequestDto createBookingRequestDto);

    public UpdateBookingResponseDto updateBooking(UpdateBookingRequestDto updateBookingRequestDto);
}
