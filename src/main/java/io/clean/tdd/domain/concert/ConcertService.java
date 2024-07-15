package io.clean.tdd.domain.concert;

import io.clean.tdd.domain.concert.model.*;
import io.clean.tdd.domain.concert.port.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcertService {

    private final ConcertRepository concertRepository;
    private final ConcertDetailRepository concertDetailRepository;
    private final SeatRepository seatRepository;
    private final ReservationRepository reservationRepository;
    private final PaymentRepository paymentRepository;
    private final ReservationAccessRepository reservationAccessRepository;

    public List<Concert> searchConcertsByConcertDetailId(Long concertDetailId) {
        ConcertDetail concertDetail = concertDetailRepository.findById(concertDetailId);
        List<Concert> concerts = concertRepository.findByConcertDetailId(concertDetailId);
        return new ArrayList<>(Arrays.asList());
    }

    public List<Seat> searchSeatsByConcertId(long concertId) {
        List<Seat> seats = seatRepository.findByConcertId(concertId);
        return new ArrayList<>(Arrays.asList());
    }

    @Transactional
    public Reservation reserveSeats(Reservation reservation) {
        // isReserved: false -> true
        Seat updatedSeat = seatRepository.update(Seat.builder().build());

        Reservation savedReservation = reservationRepository.save(reservation);

        return Reservation.builder().build();
    }

    @Transactional
    public Payment proceedPayment(Payment payment) {
        Payment paymentRecord = paymentRepository.save(payment);

        // isClosed: false -> true
        Reservation reservation = reservationRepository.getById(payment.reservationId());
        Reservation updatedReservation = reservationRepository.update(Reservation.builder()
            .isClosed(true)
            .build());

        // HOLDING -> EXPIRED
        ReservationAccess reservationAccess = reservationAccessRepository.getByUserId(reservation.userId());
        ReservationAccess closedReservedAccess = reservationAccessRepository.update(reservationAccess.close());

        return Payment.builder().build();
    }
}
