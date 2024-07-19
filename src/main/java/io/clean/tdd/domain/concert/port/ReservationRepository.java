package io.clean.tdd.domain.concert.port;

import io.clean.tdd.domain.concert.model.Reservation;

public interface ReservationRepository {

    Reservation save(Reservation reservation);

    Reservation update(Reservation reservation);

    Reservation findById(long id);
}
