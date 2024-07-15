package io.clean.tdd.domain.concert.port;

import io.clean.tdd.domain.concert.model.Seat;

import java.util.List;

public interface SeatRepository {

    List<Seat> findByConcertId(long concertId);

    Seat update(Seat seat);
}
