package io.clean.tdd.domain.concert.model;

import java.time.LocalDateTime;

public record ReservationAccess(
    long id,
    String tokenId,
    ReservationAccessStatus status,
    LocalDateTime createdAt,
    LocalDateTime expiresAt,
    long userId
) {
}
