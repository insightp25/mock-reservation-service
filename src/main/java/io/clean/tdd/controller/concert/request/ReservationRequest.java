package io.clean.tdd.controller.concert.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.clean.tdd.domain.concert.model.Reservation;
import io.clean.tdd.domain.concert.model.ReservationStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public record ReservationRequest(
    @JsonProperty("user_id") long userId,
    @JsonProperty("seat_ids") List<Long> seatIds
) {
    @Builder
    public ReservationRequest {
    }

    public Reservation toModel(long concertId) {
        return Reservation.builder()
            .concertId(concertId)
            .status(ReservationStatus.PENDING)
            .createdAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
            .userId(userId)
            .seatIds(seatIds)
            .build();
    }
}
