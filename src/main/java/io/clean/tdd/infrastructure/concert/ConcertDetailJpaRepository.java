package io.clean.tdd.infrastructure.concert;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertDetailJpaRepository extends JpaRepository<ConcertDetailEntity, Long> {

}
