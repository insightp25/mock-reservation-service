package io.clean.tdd.domain.concert.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record Reservation(
    long id,
    ReservationStatus status,
    LocalDateTime createdAt,
    long userId,
    long concertId,
    List<Long> seatIds
) {
    @Builder
    public Reservation {
    }
}
