package io.clean.tdd.domain.common;

public record ErrorResponse(
    String code,
    String message
) {
}
