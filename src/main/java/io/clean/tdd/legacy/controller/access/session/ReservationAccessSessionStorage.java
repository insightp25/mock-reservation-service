//package io.clean.tdd.legacy.controller.access.session;
//
//import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//public class ReservationAccessSessionStorage {
//
//    private final HttpSession httpSession;
//
//    public void storeSession(String reservationAccessTokenId) {
//        httpSession.setAttribute(ReservationAccessSessionConstant.RESERVATION_ACCESS_TOKEN_ID, reservationAccessTokenId);
//    }
//
//    public void discardSession() {
//        httpSession.removeAttribute(ReservationAccessSessionConstant.RESERVATION_ACCESS_TOKEN_ID);
//    }
//
//    public String getSessionReservationAccessTokenId() {
//        return (String) httpSession.getAttribute(
//            ReservationAccessSessionConstant.RESERVATION_ACCESS_TOKEN_ID);
//    }
//}
