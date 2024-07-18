package io.clean.tdd.domain.concert.model;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record Seat(
    long id,
    int seatNumber,
    SeatStatus seatStatus,
    LocalDateTime createdAt,
    SeatOption seatOption,
    long concertId,
    long reservationId
) {
    @Builder
    public Seat {
    }

    public static List<Seat> listToHoldingStatus(List<Seat> seats, long reservationId) {
        return seats.stream()
            .map(seat -> seat.toHoldingStatus(reservationId))
            .toList();
    }

    public static List<Seat> listToOccupiedStatus(List<Seat> seats, long reservationId) {
        return seats.stream()
            .map(seat -> seat.toOccupiedStatus(reservationId))
            .toList();
    }

    public Seat toHoldingStatus(long reservationId) {
        return Seat.builder()
            .id(id)
            .seatNumber(seatNumber)
            .seatStatus(SeatStatus.ON_HOLD)
            .createdAt(createdAt)
            .seatOption(seatOption)
            .reservationId(reservationId)
            .build();
    }

    public Seat toOccupiedStatus(long reservationId) {
        return Seat.builder()
            .id(id)
            .seatNumber(seatNumber)
            .seatStatus(SeatStatus.OCCUPIED)
            .createdAt(createdAt)
            .seatOption(seatOption)
            .reservationId(reservationId)
            .build();
    }
}
