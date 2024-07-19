package io.clean.tdd.domain.concert.model;

import lombok.Builder;

public record User(
    long id
) {
    @Builder
    public User {
    }
}
