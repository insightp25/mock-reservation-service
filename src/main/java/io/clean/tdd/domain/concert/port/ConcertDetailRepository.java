package io.clean.tdd.domain.concert.port;

import io.clean.tdd.domain.concert.model.ConcertDetail;

public interface ConcertDetailRepository {

    ConcertDetail findById(long id);
}
