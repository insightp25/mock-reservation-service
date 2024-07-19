package io.clean.tdd.infrastructure.payment;

import io.clean.tdd.infrastructure.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Long> {

}
