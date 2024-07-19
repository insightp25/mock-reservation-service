package io.clean.tdd.infrastructure.concert;

import io.clean.tdd.infrastructure.concert.entity.ConcertDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertDetailJpaRepository extends JpaRepository<ConcertDetailEntity, Long> {

}
