package io.clean.tdd.infrastructure.payment;

import io.clean.tdd.domain.payment.model.PointHistory;
import io.clean.tdd.domain.payment.port.PointHistoryRepository;
import io.clean.tdd.infrastructure.payment.entity.PointHistoryEntity;
import java.util.List;
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

    @Override
    public List<PointHistory> selectAllByUserId(Long userId) {
        return pointHistoryJpaRepository.findAllByUserId(userId).stream().map(PointHistoryEntity::toModel).toList();
    }
}
