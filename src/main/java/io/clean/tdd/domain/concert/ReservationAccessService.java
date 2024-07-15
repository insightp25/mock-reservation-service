package io.clean.tdd.domain.concert;

import io.clean.tdd.domain.concert.port.ReservationAccessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationAccessService {

    private final ReservationAccessRepository reservationAccessRepository;

    public void setToken(String id) {
    }

    public String getToken(String id) {
        return null;
    }

    public void deleteToken(String id) {
    }

    public void saveToken(String id) {
    }
}
