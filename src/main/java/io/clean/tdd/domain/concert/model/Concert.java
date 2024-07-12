package io.clean.tdd.domain.concert.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record Concert(
    long id,
    LocalDateTime heldAt,
    int seatCapacity,
    int ticketPrice,
    long concertDetail,
    List<Seat> reservedSeats
) {
    @Builder
    public Concert {
    }
}
