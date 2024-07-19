package io.clean.tdd.domain.user.model;

import lombok.Builder;

public record User(
    long id
) {
    @Builder
    public User {
    }
}
