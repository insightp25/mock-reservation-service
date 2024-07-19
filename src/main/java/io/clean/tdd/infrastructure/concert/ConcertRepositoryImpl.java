package io.clean.tdd.infrastructure.concert;

import io.clean.tdd.domain.concert.model.Concert;
import io.clean.tdd.domain.concert.port.ConcertRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ConcertRepositoryImpl implements ConcertRepository {

    private final ConcertJpaRepository concertJpaRepository;

    @Override
    public List<Concert> findByConcertDetailId(long concertDetailId) {
        return concertJpaRepository.findByConcertDetailId(concertDetailId);
    }
}
