package io.clean.tdd.domain.concert.port;

import io.clean.tdd.domain.concert.model.UserPoint;

public interface UserPointRepository {
    UserPoint selectById(long userId);

    UserPoint insertOrUpdate(UserPoint userPoint);
}
