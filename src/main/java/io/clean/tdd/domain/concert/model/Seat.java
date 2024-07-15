package io.clean.tdd.domain.concert.model;

import lombok.Builder;

import java.time.LocalDateTime;

public record Seat(
    long id,
    int seatNumber,
    boolean isReserved,
    LocalDateTime createdAt,
    long concertId,
    long reservationId
) {
    @Builder
    public Seat {
    }
}
