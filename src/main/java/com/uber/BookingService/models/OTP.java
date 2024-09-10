package com.uber.BookingService.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Random;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OTP extends BaseModel {

    @Column(unique = true, nullable = false)
    private String code;

    private String sentToNumber;

    public static OTP create(String phoneNumber) {
        Random random = new Random();

        // generate random number from 0 to 8999
        int code = random.nextInt(9000) + 1000;

        return OTP.builder()
                  .code(Integer.toString(code))
                  .sentToNumber(phoneNumber)
                  .build();
    }
}
