package io.clean.tdd.controller.concert.session;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import static io.clean.tdd.controller.concert.session.ReservationAccessSessionConstant.RESERVATION_ACCESS_ID;

@RequiredArgsConstructor
public class ReservationAccessSessionStorage {

    private final HttpSession httpSession;

    public void storeSession(String reservationAccessId) {
        httpSession.setAttribute(RESERVATION_ACCESS_ID, reservationAccessId);
    }

    public void discardSession() {
        httpSession.removeAttribute(RESERVATION_ACCESS_ID);
    }

    public String getSessionReservationAccessId() {
        return (String) httpSession.getAttribute(RESERVATION_ACCESS_ID);
    }
}
