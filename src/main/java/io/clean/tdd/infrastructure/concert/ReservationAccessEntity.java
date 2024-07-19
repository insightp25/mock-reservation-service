package io.clean.tdd.infrastructure.concert;

import io.clean.tdd.domain.concert.model.ReservationAccess;
import io.clean.tdd.domain.concert.model.ReservationAccessStatus;
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
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation_access")
public class ReservationAccessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "token_id", nullable = false)
    String tokenId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    ReservationAccessStatus status;

    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;

    @Column(name = "expires_at", nullable = false)
    LocalDateTime expiresAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity userEntity;

    public static ReservationAccessEntity from(ReservationAccess reservationAccess, UserEntity userEntity) {
        ReservationAccessEntity reservationAccessEntity = new ReservationAccessEntity();
        reservationAccessEntity.id = reservationAccess.id();
        reservationAccessEntity.tokenId = reservationAccess.tokenId();
        reservationAccessEntity.status = reservationAccess.status();
        reservationAccessEntity.createdAt = reservationAccess.createdAt();
        reservationAccessEntity.expiresAt = reservationAccess.expiresAt();
        reservationAccessEntity.userEntity = userEntity;

        return reservationAccessEntity;
    }

    public ReservationAccess toModel() {
        return ReservationAccess.builder()
            .id(id)
            .tokenId(tokenId)
            .status(status)
            .createdAt(createdAt)
            .expiresAt(expiresAt)
            .userId(userEntity.id)
            .build();
    }
}
