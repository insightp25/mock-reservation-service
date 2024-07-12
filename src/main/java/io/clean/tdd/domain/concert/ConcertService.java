package io.clean.tdd.domain.concert;

import io.clean.tdd.domain.concert.model.Concert;
import io.clean.tdd.domain.concert.model.Payment;
import io.clean.tdd.domain.concert.model.Reservation;
import io.clean.tdd.domain.concert.model.Seat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcertService {

    public List<Concert> searchByConcertDetailId(Long concertDetailId) {
        return null;
    }

    public List<Seat> searchReservedSeatsByConcertId(long id) {
        return null;
    }

    public Reservation reserveSeats(Reservation reservation) {
        return null;
    }

    public Payment proceedPayment(Payment payment) {
        return null;
    }
}
