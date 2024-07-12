package io.clean.tdd.domain.concert.model;

import lombok.Builder;

import java.time.LocalDateTime;

public record Payment(
    Long id,
    Long amount,
    LocalDateTime createdAt,
    Long reservationId
) {
    @Builder
    public Payment {
    }
}
