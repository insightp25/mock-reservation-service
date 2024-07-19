package io.clean.tdd.controller.access.aspect;

import io.clean.tdd.controller.access.session.ReservationAccessSessionStorage;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Aspect
@Component
@RequiredArgsConstructor
public class ReservationAccessCheckAspect {

    private final ReservationAccessSessionStorage reservationAccessSessionStorage;

    @Before("@annotation(io.clean.tdd.ReservationAccessCheck)")
    public void checkEligibility() throws HttpClientErrorException { // to be finalized

        String reservationAccessId = reservationAccessSessionStorage.getSessionReservationAccessId();

        // to be finalized
        if (reservationAccessId == null) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }
}
