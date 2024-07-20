package io.clean.tdd.infrastructure.payment.entity;

import io.clean.tdd.domain.payment.model.UserPoint;
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
    public Long userId;

    @Column(name = "point", nullable = false)
    public Long point;

    @Column(name = "updated_at", nullable = false)
    public LocalDateTime updatedAt;

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
