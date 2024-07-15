package io.clean.tdd.domain.concert.port;

import io.clean.tdd.domain.concert.model.Concert;

import java.util.List;

public interface ConcertRepository {

    List<Concert> findByConcertDetailId(long id);
}
