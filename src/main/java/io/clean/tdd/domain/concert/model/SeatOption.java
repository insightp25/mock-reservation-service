package io.clean.tdd.domain.concert.model;

import lombok.Builder;

public record SeatOption(
    long id,
    SeatClass seatClass,
    long price
) {
    @Builder
    public SeatOption {
    }
}
