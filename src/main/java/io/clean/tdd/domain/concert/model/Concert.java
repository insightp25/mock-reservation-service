package io.clean.tdd.domain.concert.model;

import lombok.Builder;

import java.time.LocalDateTime;

public record Concert(
    long id,
    LocalDateTime occasion,
    String location,
    int seatCapacity,
    int reservedSeatCount,
    long concertDetailId
) {
    @Builder
    public Concert {
    }
}
