package io.clean.tdd.domain.concert.model;

import lombok.Builder;

public record ConcertDetail(
    long id,
    String title,
    String description
) {
    @Builder
    public ConcertDetail {
    }
}
