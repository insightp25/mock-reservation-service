package io.clean.tdd.domain.payment.port;

import io.clean.tdd.domain.payment.model.PointHistory;

public interface PointHistoryRepository {
    void insert(PointHistory pointHistory);
}
