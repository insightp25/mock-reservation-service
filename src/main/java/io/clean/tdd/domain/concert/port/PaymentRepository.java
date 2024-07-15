package io.clean.tdd.domain.concert.port;

import io.clean.tdd.domain.concert.model.Payment;

public interface PaymentRepository {
    Payment save(Payment payment);
}
