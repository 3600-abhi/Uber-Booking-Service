package com.uber.BookingService.controllers;

import com.uber.BookingService.constant.AppConstant;
import com.uber.BookingService.dtos.CreateBookingRequestDto;
import com.uber.BookingService.dtos.CreateBookingResponseDto;
import com.uber.BookingService.dtos.UpdateBookingRequestDto;
import com.uber.BookingService.dtos.UpdateBookingResponseDto;
import com.uber.BookingService.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/booking/")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping(AppConstant.CREATE_BOOKING)
    public ResponseEntity<CreateBookingResponseDto> createBooking(@RequestBody CreateBookingRequestDto createBookingRequestDto) {
        return new ResponseEntity<>(bookingService.createBooking(createBookingRequestDto), HttpStatus.CREATED);
    }

    @PostMapping(AppConstant.UPDATE_BOOKING)
    public ResponseEntity<UpdateBookingResponseDto> updateBooking(@RequestBody UpdateBookingRequestDto updateBookingRequestDto) {
        return new ResponseEntity<>(bookingService.updateBooking(updateBookingRequestDto), HttpStatus.OK);
    }
}
