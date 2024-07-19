package io.clean.tdd.infrastructure.payment;

import io.clean.tdd.infrastructure.payment.entity.PointHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointHistoryJpaRepository extends JpaRepository<PointHistoryEntity, Long> {

}
