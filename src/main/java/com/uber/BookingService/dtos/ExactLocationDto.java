package com.uber.BookingService.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExactLocationDto {
    private Double latitude;
    private Double longitude;
}
