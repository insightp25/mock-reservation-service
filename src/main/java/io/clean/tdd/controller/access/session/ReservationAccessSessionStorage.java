package io.clean.tdd.controller.access.session;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReservationAccessSessionStorage {

    private final HttpSession httpSession;

    public void storeSession(String reservationAccessId) {
        httpSession.setAttribute(ReservationAccessSessionConstant.RESERVATION_ACCESS_ID, reservationAccessId);
    }

    public void discardSession() {
        httpSession.removeAttribute(ReservationAccessSessionConstant.RESERVATION_ACCESS_ID);
    }

    public String getSessionReservationAccessId() {
        return (String) httpSession.getAttribute(
            ReservationAccessSessionConstant.RESERVATION_ACCESS_ID);
    }
}
