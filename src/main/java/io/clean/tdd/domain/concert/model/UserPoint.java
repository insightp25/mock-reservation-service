package io.clean.tdd.domain.concert.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public record UserPoint(
    long id,
    long point,
    LocalDateTime updatedAt
) {
    @Builder
    public UserPoint {
    }

    public static UserPoint empty(
        long id
    ) {
        return new UserPoint(id, 0, LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    }

    public UserPoint rebalanceForUse(long amount) {
        return UserPoint.builder()
            .id(id)
            .point(point - amount)
            .updatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
            .build();
    }

    public UserPoint rebalanceForCharge(long amount) {
        return UserPoint.builder()
            .id(id)
            .point(point + amount)
            .updatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
            .build();
    }
}
