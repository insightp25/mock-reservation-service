package io.clean.tdd.controller.concert.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.clean.tdd.domain.concert.model.Reservation;
import io.clean.tdd.domain.concert.model.ReservationStatus;
import io.clean.tdd.domain.concert.model.Seat;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public record ReservationRequest(
    @JsonProperty("user_id") long userId,
    @JsonProperty("seats") List<Seat> seats
) {
    @Builder
    public ReservationRequest {
    }

    public Reservation toModel(long concertId) { // 인자 재고
        return Reservation.builder()
            .status(ReservationStatus.VOID)
            .createdAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
            .userId(userId)
            .seats(seats)
            .build();
    }
}
