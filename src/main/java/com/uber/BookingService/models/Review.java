package com.uber.BookingService.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "bookingReview")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review extends BaseModel {

    @Column(nullable = false)
    private String content;

    private Double rating;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // we have defined one to one relationship between booking review
    @JoinColumn(nullable = false)
    private Booking booking;

}
