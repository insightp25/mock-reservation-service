package io.clean.tdd.domain.payment;

import io.clean.tdd.domain.payment.model.UserPoint;
import org.springframework.stereotype.Component;

@Component
public class PointValidator { // 이후 패키지 리팩토링(서비스와 같은 위계 부적절)
    public void validatePointGreaterThanZero(long amount) {
        if (amount <= 0) {
            throw new RuntimeException(); // 이후 리팩토링
        }
    }

    public void validateSufficientPoints(long amount, UserPoint userPoint) {
        if (userPoint.point() < amount) {
            throw new RuntimeException(); // 이후 리팩토링
        }
    }
}
