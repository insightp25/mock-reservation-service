package io.clean.tdd.domain.payment.model;

import io.clean.tdd.domain.concert.model.Reservation;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public record Payment(
    Long id,
    Long amount,
    LocalDateTime createdAt,
    long reservationId
) {
    @Builder
    public Payment {
    }

    public static Payment generatePayment(Reservation reservation) {
        return Payment.builder()
            .amount(reservation.calculatePrice())
            .createdAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
            .reservationId(reservation.id())
            .build();
    }
}
