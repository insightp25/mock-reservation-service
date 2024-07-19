package io.clean.tdd.infrastructure.concert;

import io.clean.tdd.infrastructure.concert.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationJpaRepository extends JpaRepository<ReservationEntity, Long> {

}
