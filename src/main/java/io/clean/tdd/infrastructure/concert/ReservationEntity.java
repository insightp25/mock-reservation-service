package io.clean.tdd.infrastructure.concert;

import io.clean.tdd.domain.concert.model.Reservation;
import io.clean.tdd.domain.concert.model.ReservationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservation")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    ReservationStatus status;

    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity userEntity;

    @OneToMany(mappedBy = "reservationEntity")
    List<SeatEntity> seatEntities = new ArrayList<>();

    public static ReservationEntity from(Reservation reservation) {
        UserEntity innerUserEntity = new UserEntity();
        innerUserEntity.id = reservation.userId();

        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.id = reservation.id();
        reservationEntity.status = reservation.status();
        reservationEntity.createdAt = reservation.createdAt();
        reservationEntity.userEntity = innerUserEntity;

        return reservationEntity;
    }

    public Reservation toModel() {
        return Reservation.builder()
            .id(id)
            .status(status)
            .createdAt(createdAt)
            .userId(userEntity.id)
            .seats(seatEntities.stream()
                .map(SeatEntity::toModel)
                .toList())
            .build();
    }
}
