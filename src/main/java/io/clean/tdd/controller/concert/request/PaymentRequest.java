package io.clean.tdd.controller.concert.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.clean.tdd.domain.payment.model.Payment;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public record PaymentRequest(
    @JsonProperty("user_id") long userId,
    @JsonProperty("due_amount") long dueAmount
) {
    @Builder
    public PaymentRequest {
    }

    public Payment toModel(Long reservationId) {
        return Payment.builder()
            .reservationId(reservationId)
            .amount(dueAmount)
            .createdAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
            .build();
    }
}
