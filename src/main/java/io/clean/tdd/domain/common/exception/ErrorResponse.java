package io.clean.tdd.domain.common.exception;

public record ErrorResponse(
    String code,
    String message
) {
}
