package io.clean.tdd.infrastructure.payment.entity;

import io.clean.tdd.domain.payment.model.Payment;
import io.clean.tdd.infrastructure.concert.entity.ReservationEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "amount", nullable = false)
    public Long amount;

    @Column(name = "created_at", nullable = false)
    public LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "reservation_id", nullable = true)
    public ReservationEntity reservationEntity;

    public static PaymentEntity from(Payment payment, ReservationEntity reservationEntity) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.id = payment.id();
        paymentEntity.amount = payment.amount();
        paymentEntity.createdAt = payment.createdAt();
        paymentEntity.reservationEntity = reservationEntity;

        return paymentEntity;
    }

    public Payment toModel() {
        return Payment.builder()
            .id(id)
            .amount(amount)
            .createdAt(createdAt)
            .reservationId(reservationEntity.id)
            .build();
    }
}
