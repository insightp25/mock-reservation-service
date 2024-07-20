package io.clean.tdd.domain.access;

import io.clean.tdd.domain.access.model.ReservationAccess;
import io.clean.tdd.domain.access.port.ReservationAccessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationAccessService {

    private final ReservationAccessRepository reservationAccessRepository;

    public void checkAccessForReservation(String tokenId) {

        ReservationAccess reservationAccess = reservationAccessRepository.findByTokenId(tokenId);

        reservationAccess.validateAccessForReservation();
    }

    public ReservationAccess checkAccessForPayment(String tokenId) {
        ReservationAccess reservationAccess = reservationAccessRepository.findByTokenId(tokenId);

        reservationAccess.validateAccessForPayment();

        return reservationAccess;
    }

    public void resetExpiration(ReservationAccess reservationAccess) {
        reservationAccessRepository.update(reservationAccess.resetExpiration());
    }

    public ReservationAccess generate(long userId) {
        return ReservationAccess.generate(userId);
    }

    public void save(ReservationAccess reservationAccess) {
        reservationAccessRepository.update(reservationAccess);
    }

//    public void setToken(String id) {
//    }
//
//    public String getToken(String id) {
//        return null;
//    }
//
//    public void deleteToken(String id) {
//    }
}
