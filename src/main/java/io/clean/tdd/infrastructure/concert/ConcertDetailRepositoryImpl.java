package io.clean.tdd.infrastructure.concert;

import io.clean.tdd.domain.concert.model.ConcertDetail;
import io.clean.tdd.domain.concert.port.ConcertDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ConcertDetailRepositoryImpl implements ConcertDetailRepository {

    private final ConcertDetailJpaRepository concertDetailJpaRepository;

    @Override
    public ConcertDetail findById(long id) {
        return null;
    }
}
