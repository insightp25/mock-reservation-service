package io.clean.tdd.domain.concert.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record Reservation(
    long id,
    ReservationStatus status, // 재고
    LocalDateTime createdAt,
    long userId,
    List<Seat> seats
) {
    @Builder
    public Reservation {
    }

    public long calculatePrice() {
        return seats.stream()
            .mapToLong(seat -> seat.seatOption().price())
            .sum();
    }

    public Reservation toHoldingStatus() {
        return Reservation.builder()
            .id(id)
            .status(ReservationStatus.HOLDING)
            .createdAt(createdAt)
            .userId(userId)
            .seats(Seat.listToHoldingStatus(seats, id))
            .build();
    }

    public Reservation toCompleteStatus() {
        return Reservation.builder()
            .id(id)
            .status(ReservationStatus.COMPLETE)
            .createdAt(createdAt)
            .userId(userId)
            .seats(Seat.listToOccupiedStatus(seats, id))
            .build();
    }
}
