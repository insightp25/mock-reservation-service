package io.clean.tdd.infrastructure.payment;

import io.clean.tdd.domain.payment.model.Payment;
import io.clean.tdd.domain.payment.port.PaymentRepository;
import io.clean.tdd.infrastructure.payment.entity.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public Payment save(Payment payment) {
        PaymentEntity paymentEntity = paymentJpaRepository.findById(payment.id()).orElseThrow();

        return paymentJpaRepository.save(PaymentEntity.from(payment, paymentEntity.reservationEntity)).toModel();
    }
}
