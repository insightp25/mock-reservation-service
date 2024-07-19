package io.clean.tdd.infrastructure.payment;

import io.clean.tdd.domain.payment.model.PointHistory;
import io.clean.tdd.infrastructure.payment.entity.PointHistoryEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PointHistoryJpaRepository extends JpaRepository<PointHistoryEntity, Long> {

    @Query("SELECT p FROM PointHistoryEntity p WHERE p.userId = :userId")
    List<PointHistoryEntity> findAllByUserId(@Param("userId") Long userId);
}
