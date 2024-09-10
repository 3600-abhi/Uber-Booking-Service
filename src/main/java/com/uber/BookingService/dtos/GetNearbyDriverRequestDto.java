package com.uber.BookingService.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetNearbyDriverRequestDto {
    private double latitude;
    private double longitude;
}
