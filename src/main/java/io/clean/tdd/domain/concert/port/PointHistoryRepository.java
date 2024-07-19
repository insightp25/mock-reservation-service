package io.clean.tdd.domain.concert.port;

import io.clean.tdd.domain.concert.model.PointHistory;

import java.util.List;

public interface PointHistoryRepository {
    void insert(PointHistory pointHistory);
}
