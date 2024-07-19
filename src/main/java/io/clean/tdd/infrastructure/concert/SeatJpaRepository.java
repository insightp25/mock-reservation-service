package io.clean.tdd.infrastructure.concert;

import io.clean.tdd.domain.concert.model.Seat;
import io.clean.tdd.infrastructure.concert.entity.SeatEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SeatJpaRepository extends JpaRepository<SeatEntity, Long> {

    @Query("SELECT s FROM SeatEntity s WHERE s.concertEntity.id = :concertId")
    List<Seat> findByConcertId(long concertId);
}
