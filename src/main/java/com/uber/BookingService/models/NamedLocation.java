package com.uber.BookingService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NamedLocation extends BaseModel {

    @OneToOne
    private ExactLocation exactLocation;

    private String name;

    private String pinCode;

    private String city;

    private String state;

    private String country;

}
