package io.clean.tdd.domain.payment.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public record PointHistory(
    long id,
    long userId,
    long amount,
    TransactionType type,
    LocalDateTime updatedAt
) {
    @Builder
    public PointHistory {
    }

    public static PointHistory generatePointUseHistory(long userId, long amount) {
        return PointHistory.builder()
            .userId(userId)
            .amount(amount)
            .type(TransactionType.USE)
            .updatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
            .build();
    }

    public static PointHistory generatePointChargeHistory(long userId, long amount) {
        return PointHistory.builder()
            .userId(userId)
            .amount(amount)
            .type(TransactionType.USE)
            .updatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
            .build();
    }
}
