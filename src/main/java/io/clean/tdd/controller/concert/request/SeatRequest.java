package io.clean.tdd.controller.concert.request;

import lombok.Builder;

public record SeatRequest(
    long userId
) {
    @Builder
    public SeatRequest {
    }
}
