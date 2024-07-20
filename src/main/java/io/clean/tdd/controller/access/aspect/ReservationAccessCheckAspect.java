package io.clean.tdd.controller.access.aspect;

import io.clean.tdd.controller.access.session.ReservationAccessSessionStorage;
import io.clean.tdd.domain.access.ReservationAccessService;
import io.clean.tdd.domain.common.exception.CustomException;
import io.clean.tdd.domain.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
@RequiredArgsConstructor
public class ReservationAccessCheckAspect {

    private final ReservationAccessSessionStorage reservationAccessSessionStorage;

//    private final ReservationAccessService reservationAccessService;

    @Before("@annotation(io.clean.tdd.controller.access.annotation.ReservationAccessCheck)")
    public void checkEligibility() {

        String reservationAccessTokenId = reservationAccessSessionStorage.getSessionReservationAccessTokenId();
//
//        if (reservationAccessTokenId == null) {
//            reservationAccessSessionStorage.storeSession(UUID.randomUUID().toString());
//            throw new CustomException(ErrorCode.ACCESS_TOKEN_NOT_FOUND);
//        }
    }
}
