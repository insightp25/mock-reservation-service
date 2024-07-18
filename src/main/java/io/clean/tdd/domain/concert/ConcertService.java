package io.clean.tdd.domain.concert;

import io.clean.tdd.domain.concert.model.*;
import io.clean.tdd.domain.concert.port.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
//        ConcertDetail concertDetail = concertDetailRepository.findById(concertDetailId);
        return concertRepository.findByConcertDetailId(concertDetailId);
    }

    public List<Seat> searchSeatsByConcertId(long concertId) {
        return seatRepository.findByConcertId(concertId);
    }

    @Transactional
    public Reservation reserveSeats(Reservation reservation) {
        // reservation status: VOID -> HOLDING
        // seats status: VOID -> ON_HOLD
        // 재고: seats의 상태도 함께 업데이트 되었는데, jpa에서 어떻게 처리하냐(1:N)에 따라 아래 seatRepository.update()가 중복이 될 수도
        Reservation resultReservation = reservationRepository.save(reservation.toHoldingStatus());


        resultReservation.seats().stream()
            .forEach(seatRepository::update);

        return resultReservation;
    }

    @Transactional
    public Payment proceedPayment(Payment payment) {
        // reservation
        // HOLDING -> EXPIRED
        Reservation reservation = reservationRepository.getById(payment.reservationId());
        Reservation updatedReservation = reservationRepository.update(reservation.toCompleteStatus());

        // seat
        // ON_HOLD -> OCCUPIED
        updatedReservation.seats().stream()
            .forEach(seatRepository::update);

        // reservation access
        // HOLDING -> EXPIRED
        ReservationAccess reservationAccess = reservationAccessRepository.getByUserId(updatedReservation.userId());
        ReservationAccess closedReservedAccess = reservationAccessRepository.update(reservationAccess.close());

        return paymentRepository.save(Payment.generatePayment(updatedReservation));
    }
}
