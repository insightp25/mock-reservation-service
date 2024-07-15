package io.clean.tdd.domain.concert.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public record ReservationAccess(
    long id,
    String tokenId,
    ReservationAccessStatus status,
    LocalDateTime createdAt,
    LocalDateTime expiresAt,
    long userId
) {
    @Builder
    public ReservationAccess {
    }

    public ReservationAccess close() {
        return ReservationAccess.builder()
            .id(id)
            .tokenId(tokenId)
            .status(ReservationAccessStatus.EXPIRED)
            .createdAt(createdAt)
            .expiresAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
            .build();
    }
}
