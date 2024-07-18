package io.clean.tdd.domain.concert;

import io.clean.tdd.domain.concert.model.PointHistory;
import io.clean.tdd.domain.concert.model.UserPoint;
import io.clean.tdd.domain.concert.port.PointHistoryRepository;
import io.clean.tdd.domain.concert.port.UserPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPointService {

    private final PointHistoryRepository pointHistoryRepository;
    private final UserPointRepository userPointRepository;
    private final PointValidator pointValidator;

    public UserPoint getByUserId(long userId) {
        return userPointRepository.selectById(userId);
    }

    public List<PointHistory> getHistoriesByUserId(long userId) {
        return pointHistoryRepository.selectAllByUserId(userId);
    }

    public UserPoint charge(long userId, long amount) {
        pointValidator.validatePointGreaterThanZero(amount);

        UserPoint userPoint = userPointRepository.selectById(userId);
        UserPoint rebalancedUserPoint = userPointRepository.insertOrUpdate(userPoint.rebalanceForCharge(amount));

        pointHistoryRepository.insert(PointHistory.generatePointChargeHistory(userId, amount));

        return rebalancedUserPoint;
    }
}
