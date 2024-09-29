package com.uber.BookingService.services;

import com.google.gson.Gson;
import com.uber.BookingService.apis.LocationServiceApi;
import com.uber.BookingService.apis.SocketServiceApi;
import com.uber.BookingService.constant.BookingStatus;
import com.uber.BookingService.dtos.*;
import com.uber.BookingService.exception.AppException;
import com.uber.BookingService.models.Booking;
import com.uber.BookingService.models.Driver;
import com.uber.BookingService.models.Passenger;
import com.uber.BookingService.repositories.BookingRepository;
import com.uber.BookingService.repositories.DriverRepository;
import com.uber.BookingService.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private LocationServiceApi locationServiceApi;

    @Autowired
    private SocketServiceApi socketServiceApi;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public CreateBookingResponseDto createBooking(CreateBookingRequestDto createBookingRequestDto) {

        Optional<Passenger> passenger = passengerRepository.findById(createBookingRequestDto.getPassengerId());

        Booking booking = Booking.builder()
                                 .bookingStatus(BookingStatus.ASSIGNING_DRIVER)
                                 .startLocation(createBookingRequestDto.getStartLocation())
                                 .endLocation(createBookingRequestDto.getEndLocation())
                                 .passenger(passenger.get())
                                 .build();

        Booking newBooking = bookingRepository.save(booking);

        GetNearbyDriverRequestDto getNearbyDriverRequestDto = GetNearbyDriverRequestDto.builder()
                                                                                       .latitude(createBookingRequestDto.getStartLocation().getLatitude())
                                                                                       .longitude(createBookingRequestDto.getStartLocation().getLongitude())
                                                                                       .build();


        Call<List<GetNearbyDriverResponseDto>> callGetNearbyDrivers = locationServiceApi.getNearbyDriversList(getNearbyDriverRequestDto);

        callGetNearbyDrivers.enqueue(new Callback<List<GetNearbyDriverResponseDto>>() {
            @Override
            public void onResponse(Call<List<GetNearbyDriverResponseDto>> call, Response<List<GetNearbyDriverResponseDto>> response) {
                List<GetNearbyDriverResponseDto> nearbyDriverList = null;

                if (response.isSuccessful()) {
                    nearbyDriverList = response.body();

                    for (GetNearbyDriverResponseDto getNearbyDriverResponseDto : nearbyDriverList) {
                        System.out.println("Nearby driverId = " + getNearbyDriverResponseDto.getDriverId());
                    }

                    List<Long> driverIdList = new ArrayList<>();

                    for (GetNearbyDriverResponseDto getNearbyDriverResponseDto : nearbyDriverList) {
                        driverIdList.add(Long.valueOf(getNearbyDriverResponseDto.getDriverId()));
                    }

                    RideRequestDto rideRequestDto = RideRequestDto.builder()
                                                                  .bookingId(booking.getId())
                                                                  .passengerId(newBooking.getPassenger().getId())
                                                                  .driverIdList(driverIdList)
                                                                  .startLocation(ExactLocationDto.builder().latitude(newBooking.getStartLocation().getLatitude()).longitude(newBooking.getStartLocation().getLongitude()).build())
                                                                  .endLocation(ExactLocationDto.builder().latitude(newBooking.getEndLocation().getLatitude()).longitude(newBooking.getEndLocation().getLongitude()).build())
                                                                  .build();

                    Call<Void> callSendRideRequestToDrivers = socketServiceApi.sendRideRequestToDrivers(rideRequestDto);

                    try {
                        Response<Void> responseSendRideRequestToDrivers = callSendRideRequestToDrivers.execute();
                    } catch (IOException e) {
                        throw new AppException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                    }

                } else {
                    System.out.println("Request failed with error : " + response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<List<GetNearbyDriverResponseDto>> call, Throwable throwable) {
                System.out.println("API call failed with : " + throwable.getMessage());
            }
        });


        return CreateBookingResponseDto.builder()
                                       .bookingId(newBooking.getId())
                                       .bookingStatus(String.valueOf(newBooking.getBookingStatus()))
                                       .driver(Optional.ofNullable(newBooking.getDriver()))
                                       .build();
    }

    @Override
    public synchronized UpdateBookingResponseDto updateBooking(UpdateBookingRequestDto updateBookingRequestDto) {
        System.out.println("updateBookingRequestDto :: " + new Gson().toJson(updateBookingRequestDto));

        Optional<Driver> driver = driverRepository.findById(updateBookingRequestDto.getDriverId());

        bookingRepository.updateBookingStatusAndDriverById(updateBookingRequestDto.getBookingId(), updateBookingRequestDto.getBookingStatus(), driver.get());

        Optional<Booking> booking = bookingRepository.findById(updateBookingRequestDto.getBookingId());

        return UpdateBookingResponseDto.builder()
                                       .bookingId(booking.get().getId())
                                       .bookingStatus(booking.get().getBookingStatus())
                                       .driverId(driver.get().getId())
                                       .build();
    }
}
