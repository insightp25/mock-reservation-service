package io.clean.tdd.infrastructure.concert;

import io.clean.tdd.domain.concert.model.PointHistory;
import io.clean.tdd.domain.concert.model.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "point_history")
public class PointHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "amount", nullable = false)
    Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    TransactionType transactionType;

    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity userEntity;

    public static PointHistoryEntity from(PointHistory pointHistory, UserEntity userEntity) {
        PointHistoryEntity pointHistoryEntity = new PointHistoryEntity();
        pointHistoryEntity.id = pointHistory.id();
        pointHistoryEntity.amount = pointHistory.amount();
        pointHistoryEntity.transactionType = pointHistory.type();
        pointHistoryEntity.updatedAt = pointHistory.updatedAt();
        pointHistoryEntity.userEntity = userEntity;

        return pointHistoryEntity;
    }

    public PointHistory toModel() {
        return PointHistory.builder()
            .id(id)
            .amount(amount)
            .type(transactionType)
            .updatedAt(updatedAt)
            .userId(userEntity.id)
            .build();
    }
}
