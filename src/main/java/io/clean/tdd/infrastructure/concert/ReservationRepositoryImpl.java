package io.clean.tdd.infrastructure.concert;

import io.clean.tdd.domain.concert.model.Reservation;
import io.clean.tdd.domain.concert.port.ReservationRepository;
import io.clean.tdd.infrastructure.concert.entity.ReservationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationJpaRepository reservationJpaRepository;

    @Override
    public Reservation save(Reservation reservation) {
        return reservationJpaRepository.save(ReservationEntity.from(reservation)).toModel();
    }

    @Override
    public Reservation update(Reservation reservation) {
        return reservationJpaRepository.save(ReservationEntity.from(reservation)).toModel();
    }

    @Override
    public Reservation findById(long id) {
        return reservationJpaRepository.findById(id).orElseThrow().toModel();
    }
}
