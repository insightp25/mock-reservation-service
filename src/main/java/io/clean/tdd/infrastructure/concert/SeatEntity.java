package io.clean.tdd.infrastructure.concert;

import io.clean.tdd.domain.concert.model.Concert;
import io.clean.tdd.domain.concert.model.Reservation;
import io.clean.tdd.domain.concert.model.Seat;
import io.clean.tdd.domain.concert.model.SeatStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "seat")
public class SeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "seat_number", nullable = false)
    Integer seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_reserved", nullable = false)
    SeatStatus status;

    @ManyToOne
    @JoinColumn(name = "seat_option_id", nullable = false)
    SeatOptionEntity seatOptionEntity;

    @ManyToOne
    @JoinColumn(name = "concert_id", nullable = false)
    ConcertEntity concertEntity;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = true)
    ReservationEntity reservationEntity;

    public static SeatEntity from(
        Seat seat,
        ConcertEntity concertEntity,
        ReservationEntity reservationEntity
    ) {
        SeatOptionEntity innerSeatOptionEntity = new SeatOptionEntity();
        innerSeatOptionEntity.id = seat.seatOption().id();
        innerSeatOptionEntity.seatClass = seat.seatOption().seatClass();
        innerSeatOptionEntity.price = seat.seatOption().price();

        SeatEntity seatEntity = new SeatEntity();
        seatEntity.id = seat.id();
        seatEntity.seatNumber = seat.seatNumber();
        seatEntity.status = seat.seatStatus();
        seatEntity.seatOptionEntity = innerSeatOptionEntity;
        seatEntity.concertEntity = concertEntity;
        seatEntity.reservationEntity = reservationEntity;

        return seatEntity;
    }

    public Seat toModel() {
        return Seat.builder()
            .id(id)
            .seatNumber(seatNumber)
            .seatStatus(status)
            .seatOption(seatOptionEntity.toModel())
            .concertId(concertEntity.id)
            .reservationId(reservationEntity.id)
            .build();
    }
}
