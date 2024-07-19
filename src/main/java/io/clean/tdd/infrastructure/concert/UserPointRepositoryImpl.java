package io.clean.tdd.infrastructure.concert;

import io.clean.tdd.domain.concert.model.UserPoint;
import io.clean.tdd.domain.concert.port.UserPointRepository;
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
