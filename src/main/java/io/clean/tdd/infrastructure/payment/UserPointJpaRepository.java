package io.clean.tdd.infrastructure.payment;

import io.clean.tdd.infrastructure.payment.entity.UserPointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPointJpaRepository extends JpaRepository<UserPointEntity, Long> {

}
