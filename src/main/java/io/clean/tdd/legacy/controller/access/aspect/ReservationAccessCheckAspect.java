//package io.clean.tdd.legacy.controller.access.aspect;
//
//import io.clean.tdd.legacy.controller.access.session.ReservationAccessSessionStorage;
//import lombok.RequiredArgsConstructor;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//@RequiredArgsConstructor
//public class ReservationAccessCheckAspect {
//
//    private final ReservationAccessSessionStorage reservationAccessSessionStorage;
//
////    private final ReservationAccessService reservationAccessService;
//
//    @Before("@annotation(io.clean.tdd.controller.access.annotation.ReservationAccessCheck)")
//    public void checkEligibility() {
//
//        String reservationAccessTokenId = reservationAccessSessionStorage.getSessionReservationAccessTokenId();
////
////        if (reservationAccessTokenId == null) {
////            reservationAccessSessionStorage.storeSession(UUID.randomUUID().toString());
////            throw new CustomException(ErrorCode.ACCESS_TOKEN_NOT_FOUND);
////        }
//    }
//}
