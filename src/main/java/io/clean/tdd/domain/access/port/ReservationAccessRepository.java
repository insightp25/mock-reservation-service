package io.clean.tdd.domain.access.port;

import io.clean.tdd.domain.access.model.ReservationAccess;

public interface ReservationAccessRepository {

    ReservationAccess getByUserId(long userId);

    ReservationAccess update(ReservationAccess reservationAccess);
}
