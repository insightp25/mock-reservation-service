package io.clean.tdd.infrastructure.concert;

import io.clean.tdd.domain.concert.model.PointHistory;
import io.clean.tdd.domain.concert.port.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PointHistoryRepositoryImpl implements PointHistoryRepository {

    private final PointHistoryJpaRepository pointHistoryJpaRepository;

    @Override
    public void insert(PointHistory pointHistory) {
        PointHistoryEntity pointHistoryEntity = pointHistoryJpaRepository.findById(
            pointHistory.userId()).orElseThrow();

        pointHistoryJpaRepository.save(PointHistoryEntity.from(pointHistory, pointHistoryEntity.userEntity)).toModel();

    }
}
