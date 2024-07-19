package io.clean.tdd.domain.concert;

import io.clean.tdd.domain.access.model.ReservationAccess;
import io.clean.tdd.domain.concert.model.*;
import io.clean.tdd.domain.payment.PointValidator;
import io.clean.tdd.domain.payment.model.Payment;
import io.clean.tdd.domain.payment.model.PointHistory;
import io.clean.tdd.domain.payment.model.UserPoint;
import io.clean.tdd.domain.concert.port.ConcertRepository;
import io.clean.tdd.domain.payment.port.PaymentRepository;
import io.clean.tdd.domain.payment.port.PointHistoryRepository;
import io.clean.tdd.domain.access.port.ReservationAccessRepository;
import io.clean.tdd.domain.concert.port.ReservationRepository;
import io.clean.tdd.domain.concert.port.SeatRepository;
import io.clean.tdd.domain.payment.port.UserPointRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcertService {

    private final ConcertRepository concertRepository;
//    private final ConcertDetailRepository concertDetailRepository;
    private final SeatRepository seatRepository;
    private final ReservationRepository reservationRepository;
    private final PaymentRepository paymentRepository;
    private final ReservationAccessRepository reservationAccessRepository;
    private final PointHistoryRepository pointHistoryRepository;
    private final UserPointRepository userPointRepository;
    private final PointValidator pointValidator; // 이후 패키지 리팩토링(서비스와 같은 위계 부적절)

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
        Reservation reservation = reservationRepository.findById(payment.reservationId());
        Reservation updatedReservation = reservationRepository.update(reservation.toCompleteStatus());

        // seat
        // ON_HOLD -> OCCUPIED
        updatedReservation.seats().stream()
            .forEach(seatRepository::update);

        // reservation access
        // HOLDING -> EXPIRED
        ReservationAccess reservationAccess = reservationAccessRepository.getByUserId(updatedReservation.userId());
        ReservationAccess closedReservedAccess = reservationAccessRepository.update(reservationAccess.close());

        // user point
        pointValidator.validatePointGreaterThanZero(updatedReservation.calculatePrice());
        UserPoint userPoint = userPointRepository.selectById(updatedReservation.userId());
        pointValidator.validateSufficientPoints(updatedReservation.calculatePrice(), userPoint);
        userPointRepository.insertOrUpdate(userPoint.rebalanceForUse(updatedReservation.calculatePrice()));

        // point history
        pointHistoryRepository.insert(
            PointHistory.generatePointUseHistory(updatedReservation.userId(), updatedReservation.calculatePrice()));

        // payment
        return paymentRepository.save(Payment.generatePayment(updatedReservation));
    }
}
