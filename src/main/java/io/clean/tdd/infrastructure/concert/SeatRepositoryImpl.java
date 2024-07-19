package io.clean.tdd.infrastructure.concert;

import io.clean.tdd.domain.concert.model.Seat;
import io.clean.tdd.domain.concert.port.SeatRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SeatRepositoryImpl implements SeatRepository {

    private final SeatJpaRepository seatJpaRepository;

    @Override
    public List<Seat> findByConcertId(long concertId) {
        return seatJpaRepository.findByConcertId(concertId);
    }

    @Override
    public Seat update(Seat seat) {
        SeatEntity seatEntity = seatJpaRepository.findById(seat.id()).orElseThrow();

        return seatJpaRepository.save(SeatEntity.from(seat, seatEntity.concertEntity, seatEntity.reservationEntity)).toModel();
    }
}
