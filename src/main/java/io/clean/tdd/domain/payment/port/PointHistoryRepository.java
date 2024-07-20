package io.clean.tdd.domain.payment.port;

import io.clean.tdd.domain.payment.model.PointHistory;
import java.util.List;

public interface PointHistoryRepository {
    void insert(PointHistory pointHistory);

    List<PointHistory> selectAllByUserId(Long userId);
}
