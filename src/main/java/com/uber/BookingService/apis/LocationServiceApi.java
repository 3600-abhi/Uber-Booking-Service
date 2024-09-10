package com.uber.BookingService.apis;

import com.uber.BookingService.constant.AppConstant;
import com.uber.BookingService.dtos.GetNearbyDriverRequestDto;
import com.uber.BookingService.dtos.GetNearbyDriverResponseDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.List;

public interface LocationServiceApi {

    @POST("/api/v1/location/getNearbyDrivers")
    Call<List<GetNearbyDriverResponseDto>> getNearbyDriversList(@Body GetNearbyDriverRequestDto getNearbyDriverRequestDto);
}
