package io.clean.tdd.domain.concert.model;

import lombok.Builder;

import java.time.LocalDateTime;

public record Seat(
    long id,
    int seatNumber,
    SeatStatus status,
    LocalDateTime createdAt,
    long concertId,
    long reservationId
) {
    @Builder
    public Seat {
    }
}
