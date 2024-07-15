package io.clean.tdd.domain.concert.port;

import io.clean.tdd.domain.concert.model.ReservationAccess;

public interface ReservationAccessRepository {

    ReservationAccess getByUserId(long userId);

    ReservationAccess update(ReservationAccess reservationAccess);
}
