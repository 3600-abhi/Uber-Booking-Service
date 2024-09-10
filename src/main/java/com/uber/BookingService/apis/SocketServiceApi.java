package com.uber.BookingService.apis;

import com.uber.BookingService.dtos.RideRequestDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SocketServiceApi {
    @POST("/api/v1/socket/raiseRideRequest")
    Call<Void> sendRideRequestToDrivers(@Body RideRequestDto rideRequestDto);
}
