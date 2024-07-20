package io.clean.tdd.infrastructure.access;

import io.clean.tdd.domain.access.model.ReservationAccess;
import io.clean.tdd.domain.access.port.ReservationAccessRepository;
import io.clean.tdd.infrastructure.access.entity.ReservationAccessEntity;
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

    @Override
    public ReservationAccess findByTokenId(String tokenId) {
        return null;
    }
}
