package io.clean.tdd.infrastructure.payment;

import io.clean.tdd.domain.payment.model.UserPoint;
import io.clean.tdd.domain.payment.port.UserPointRepository;
import io.clean.tdd.infrastructure.payment.entity.UserPointEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserPointRepositoryImpl implements UserPointRepository {

    private final UserPointJpaRepository userPointJpaRepository;

    @Override
    public UserPoint selectById(long userId) {
        return userPointJpaRepository.findById(userId).orElseThrow().toModel();
    }

    @Override
    public UserPoint insertOrUpdate(UserPoint userPoint) {
        return userPointJpaRepository.save(UserPointEntity.from(userPoint)).toModel();
    }
}
