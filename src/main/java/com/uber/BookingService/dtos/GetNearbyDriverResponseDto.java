package com.uber.BookingService.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetNearbyDriverResponseDto {
    private String driverId;
    private double latitude;
    private double longitude;
}
