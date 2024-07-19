package io.clean.tdd.infrastructure.concert;

import io.clean.tdd.domain.concert.model.UserPoint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_point")
public class UserPointEntity {

    @Id
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    Long userId;

    @Column(name = "point", nullable = false)
    Long point;

    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;

    public static UserPointEntity from(UserPoint userPoint) {
        UserPointEntity userPointEntity = new UserPointEntity();
        userPointEntity.userId = userPoint.id();
        userPointEntity.point = userPoint.point();
        userPointEntity.updatedAt = userPoint.updatedAt();

        return userPointEntity;
    }

    public UserPoint toModel() {
        return UserPoint.builder()
            .id(userId)
            .point(point)
            .updatedAt(updatedAt)
            .build();
    }
}
