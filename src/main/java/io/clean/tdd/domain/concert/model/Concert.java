package io.clean.tdd.domain.concert.model;

import lombok.Builder;

import java.time.LocalDateTime;

public record Concert(
    long id,
    LocalDateTime heldAt,
    int seatCapacity,
    int ticketPrice,
    long concertDetailId
) {
    @Builder
    public Concert {
    }
}
