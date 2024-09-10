package com.uber.BookingService.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Config extends BaseModel {

    @Column(unique = true)
    private String name;

    private String value;
}
