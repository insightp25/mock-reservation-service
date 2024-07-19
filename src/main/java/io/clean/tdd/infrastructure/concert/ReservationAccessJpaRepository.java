package io.clean.tdd.infrastructure.concert;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationAccessJpaRepository extends
    JpaRepository<ReservationAccessEntity, Long> {

    @Query("SELECT r FROM ReservationAccessEntity r WHERE r.user_id = :userId")
    ReservationAccessEntity getByUserId(@Param("userId") Long userId);
}
