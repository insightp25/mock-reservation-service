package io.clean.tdd.infrastructure.concert;

import io.clean.tdd.domain.concert.model.Payment;
import io.clean.tdd.domain.concert.port.PaymentRepository;
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
