package io.clean.tdd.domain.access.model;

import io.clean.tdd.domain.common.CustomException;
import io.clean.tdd.domain.common.ErrorCode;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

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

    public static ReservationAccess generate(long userId) {
        return ReservationAccess.builder()
            .tokenId(UUID.randomUUID().toString())
            .status(ReservationAccessStatus.PENDING)
            .createdAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
            .expiresAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).plusMinutes(15))
            .userId(userId)
            .build();
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

    public void validateAccessForReservation() {
        if (!status.equals(ReservationAccessStatus.ACCESSIBLE)) {
            throw new CustomException(ErrorCode.INACCESSIBLE_RESERVATION_ERROR);
        }
        if (expiresAt.isAfter(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))) {
            throw new CustomException(ErrorCode.EXPIRED_TOKEN_ERROR);
        }
    }

    public ReservationAccess resetExpiration() {
        return ReservationAccess.builder()
            .id(id)
            .tokenId(tokenId)
            .status(status)
            .createdAt(createdAt)
            .expiresAt(LocalDateTime.now().plusMinutes(5).truncatedTo(ChronoUnit.SECONDS))
            .userId(userId)
            .build();
    }

    public void validateAccessForPayment() {
        if (!status.equals(ReservationAccessStatus.HOLDING)) {
            throw new CustomException(ErrorCode.INACCESSIBLE_RESERVATION_ERROR);
        }
        if (expiresAt.isAfter(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))) {
            throw new CustomException(ErrorCode.EXPIRED_TOKEN_ERROR);
        }
    }
}
