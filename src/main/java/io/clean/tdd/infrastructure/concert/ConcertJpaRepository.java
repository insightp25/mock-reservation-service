package io.clean.tdd.infrastructure.concert;

import io.clean.tdd.domain.concert.model.Concert;
import io.clean.tdd.infrastructure.concert.entity.ConcertEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConcertJpaRepository extends JpaRepository<ConcertEntity, Long> {

    @Query("SELECT c FROM ConcertEntity c WHERE c.concertDetailEntity.id = :concertDetailId")
    List<Concert> findByConcertDetailId(@Param("concertDetailId") long concertDetailId);
}
