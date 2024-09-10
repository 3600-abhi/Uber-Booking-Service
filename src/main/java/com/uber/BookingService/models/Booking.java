package com.uber.BookingService.models;

import com.uber.BookingService.constant.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking extends BaseModel {

    // default it takes as tinyint because it automatically detects that BookingStatus is an enum
    @Enumerated(value = EnumType.STRING) // default enum mapping will be Oridnal ie numeric index
    private BookingStatus bookingStatus;

    // by default java Date (private Date startTime) will mapped to timestamp
    @Temporal(value = TemporalType.DATE)
    // this annotation tells spring about format of Date stored ie, Date, Time Or Timestamp
    private Date startTime;

    // by default java Date (private Date endTime) will mapped to timestamp
    @Temporal(value = TemporalType.DATE)
    // this annotation tells spring about format of Date stored ie, Date, Time Or Timestamp
    private Date endTime;

    private Long totalDistance;

    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    private Passenger passenger;

    @OneToOne(cascade = CascadeType.ALL)
    private ExactLocation startLocation;

    @OneToOne(cascade = CascadeType.ALL)
    private ExactLocation endLocation;
}