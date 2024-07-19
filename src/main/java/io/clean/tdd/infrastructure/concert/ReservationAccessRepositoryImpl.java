package io.clean.tdd.infrastructure.concert;

import io.clean.tdd.domain.concert.model.ReservationAccess;
import io.clean.tdd.domain.concert.port.ReservationAccessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationAccessRepositoryImpl implements ReservationAccessRepository {

    private final ReservationAccessJpaRepository reservationAccessJpaRepository;

    @Override
    public ReservationAccess getByUserId(long userId) {
        return reservationAccessJpaRepository.getByUserId(userId).toModel();
    }

    @Override
    public ReservationAccess update(ReservationAccess reservationAccess) {
        ReservationAccessEntity reservationAccessEntity = reservationAccessJpaRepository.findById(
            reservationAccess.id()).orElseThrow();

        return reservationAccessJpaRepository.save(ReservationAccessEntity.from(reservationAccess, reservationAccessEntity.userEntity)).toModel();
    }
}
