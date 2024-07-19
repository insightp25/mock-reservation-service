package io.clean.tdd.domain.payment.port;

import io.clean.tdd.domain.payment.model.Payment;

public interface PaymentRepository {
    Payment save(Payment payment);
}
