package io.clean.tdd.domain.payment.port;

import io.clean.tdd.domain.payment.model.UserPoint;

public interface UserPointRepository {
    UserPoint selectById(long userId);

    UserPoint insertOrUpdate(UserPoint userPoint);
}
