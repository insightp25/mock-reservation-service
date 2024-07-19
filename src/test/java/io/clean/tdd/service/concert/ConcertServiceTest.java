package io.clean.tdd.service.concert;

import io.clean.tdd.domain.concert.ConcertService;
import io.clean.tdd.domain.concert.PointValidator;
import io.clean.tdd.domain.concert.model.*;
import io.clean.tdd.domain.concert.port.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ConcertServiceTest {

    private static final String RANDOM_TOKEN_ID_STRING = "AAA";
    private static final long RANDOM_RESERVATION_ACCESS_ID_1L = 1L;
    private static final long RANDOM_USER_ID_1L = 1L;
    private static final long RANDOM_CONCERT_DETAIL_ID_1L = 1L;
    private static final long RANDOM_CONCERT_ID_1L = 1L;
    private static final long RANDOM_CONCERT_ID_2L = 2L;
    private static final long RANDOM_CONCERT_ID_3L = 3L;
    private static final long RANDOM_SEAT_ID_1L = 1L;
    private static final long RANDOM_SEAT_ID_2L = 2L;
    private static final long RANDOM_SEAT_ID_3L = 3L;
    private static final long RANDOM_SEAT_OPTION_ID_1L = 1L;
    private static final long RANDOM_SEAT_OPTION_ID_2L = 2L;
    private static final long RANDOM_SEAT_OPTION_ID_3L = 3L;
    private static final long RANDOM_RESERVATION_ID_1L = 1L;
    private static final long RANDOM_PAYMENT_ID_1L = 1L;
    private static final int RANDOM_SEAT_NUMBER_1 = 1;
    private static final int RANDOM_SEAT_NUMBER_2 = 2;
    private static final int RANDOM_SEAT_NUMBER_3 = 3;

    @InjectMocks
    private ConcertService concertService;

    @Mock
    private ConcertRepository concertRepository;

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ReservationAccessRepository reservationAccessRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private UserPointRepository userPointRepository;

    @Mock
    private PointHistoryRepository pointHistoryRepository;

    @Mock
    private PointValidator pointValidator; // 이후 패키지 리팩토링(서비스와 같은 위계 부적절)

    @Test
    void 특정_콘서트에_대해_예약_가능한_날짜_목록을_조회할_수_있다() {
        // given
        Concert concert1 = Concert.builder()
            .id(RANDOM_CONCERT_ID_1L)
            .concertDetailId(RANDOM_CONCERT_DETAIL_ID_1L)
            .build();
        Concert concert2 = Concert.builder()
            .id(RANDOM_CONCERT_ID_2L)
            .concertDetailId(RANDOM_CONCERT_DETAIL_ID_1L)
            .build();
        Concert concert3 = Concert.builder()
            .id(RANDOM_CONCERT_ID_3L)
            .concertDetailId(RANDOM_CONCERT_DETAIL_ID_1L)
            .build();

        BDDMockito.given(concertRepository.findByConcertDetailId(RANDOM_CONCERT_DETAIL_ID_1L))
            .willReturn(new ArrayList<>(Arrays.asList(concert1, concert2, concert3)));

        // when
        List<Concert> result = concertService.searchConcertsByConcertDetailId(RANDOM_CONCERT_DETAIL_ID_1L);

        // then
        Assertions.assertThat(result).hasSize(3);
    }

    @Test
    void 콘서트_상세정보_ID를_입력받아_해당_콘서트의_예약_가능한_좌석_목록을_조회할_수_있다() {
        // given
        Seat seat1 = Seat.builder()
            .id(RANDOM_SEAT_ID_1L)
            .seatNumber(RANDOM_SEAT_NUMBER_1)
            .concertId(RANDOM_CONCERT_ID_1L)
            .build();
        Seat seat2 = Seat.builder()
            .id(RANDOM_SEAT_ID_2L)
            .seatNumber(RANDOM_SEAT_NUMBER_2)
            .concertId(RANDOM_CONCERT_ID_1L)
            .build();
        Seat seat3 = Seat.builder()
            .id(RANDOM_SEAT_ID_3L)
            .seatNumber(RANDOM_SEAT_NUMBER_3)
            .concertId(RANDOM_CONCERT_ID_1L)
            .build();

        BDDMockito.given(seatRepository.findByConcertId(RANDOM_CONCERT_ID_1L))
            .willReturn(new ArrayList<>(Arrays.asList(seat1, seat2, seat3)));

        // when
        List<Seat> result = concertService.searchSeatsByConcertId(RANDOM_CONCERT_ID_1L);

        // then
        Assertions.assertThat(result).hasSize(3);
    }

    @Test
    void 특정_콘서트_예약_정보를_입력받아_좌석을_예약_처리할_수_있다() {
        // given
        Seat seat1 = Seat.builder()
            .id(RANDOM_SEAT_ID_1L)
            .seatNumber(RANDOM_SEAT_NUMBER_1)
            .seatStatus(SeatStatus.ON_HOLD)
            .concertId(RANDOM_CONCERT_ID_1L)
            .build();
        Seat seat2 = Seat.builder()
            .id(RANDOM_SEAT_ID_2L)
            .seatNumber(RANDOM_SEAT_NUMBER_2)
            .seatStatus(SeatStatus.ON_HOLD)
            .concertId(RANDOM_CONCERT_ID_1L)
            .build();
        Seat seat3 = Seat.builder()
            .id(RANDOM_SEAT_ID_3L)
            .seatNumber(RANDOM_SEAT_NUMBER_3)
            .seatStatus(SeatStatus.ON_HOLD)
            .concertId(RANDOM_CONCERT_ID_1L)
            .build();

        Reservation reservation = Reservation.builder()
            .id(RANDOM_RESERVATION_ID_1L)
            .status(ReservationStatus.HOLDING)
            .createdAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
            .userId(RANDOM_USER_ID_1L)
            .seats(new ArrayList<>(Arrays.asList(seat1, seat2, seat3)))
            .build();

        BDDMockito.given(reservationRepository.save(any()))
                .willReturn(reservation.toHoldingStatus());

        BDDMockito.given(seatRepository.update(seat1.toHoldingStatus(RANDOM_RESERVATION_ID_1L)))
                .willReturn(reservation.toHoldingStatus().seats().get(0));
        BDDMockito.given(seatRepository.update(seat2.toHoldingStatus(RANDOM_RESERVATION_ID_1L)))
                .willReturn(reservation.toHoldingStatus().seats().get(1));
        BDDMockito.given(seatRepository.update(seat3.toHoldingStatus(RANDOM_RESERVATION_ID_1L)))
                .willReturn(reservation.toHoldingStatus().seats().get(2));

        // when
        Reservation result = concertService.reserveSeats(reservation);

        // then
        assertAll(
            () -> Assertions.assertThat(result.status()).isEqualTo(ReservationStatus.HOLDING),
            () -> Assertions.assertThat(result.seats()).hasSize(3),
            () -> Assertions.assertThat(result.seats().get(0).seatStatus()).isEqualTo(SeatStatus.ON_HOLD),
            () -> Assertions.assertThat(result.seats().get(1).seatStatus()).isEqualTo(SeatStatus.ON_HOLD),
            () -> Assertions.assertThat(result.seats().get(2).seatStatus()).isEqualTo(SeatStatus.ON_HOLD),
            () -> Assertions.assertThat(result.seats().get(0).reservationId()).isEqualTo(RANDOM_RESERVATION_ID_1L),
            () -> Assertions.assertThat(result.seats().get(1).reservationId()).isEqualTo(RANDOM_RESERVATION_ID_1L),
            () -> Assertions.assertThat(result.seats().get(2).reservationId()).isEqualTo(RANDOM_RESERVATION_ID_1L)
        );
    }

    @Test
    void 예약한_좌석을_결제_처리하고_결제_내역을_반환할_수_있다() {
        // given
        ReservationAccess reservationAccess = ReservationAccess.builder()
            .id(RANDOM_RESERVATION_ACCESS_ID_1L)
            .tokenId(RANDOM_TOKEN_ID_STRING)
            .status(ReservationAccessStatus.ACCESSIBLE)
            .createdAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).minusMinutes(5))
            .expiresAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).plusMinutes(5))
            .userId(RANDOM_USER_ID_1L)
            .build();

        SeatOption seatOption1 = SeatOption.builder()
            .id(RANDOM_SEAT_OPTION_ID_1L)
            .price(100_000L)
            .seatClass(SeatClass.STANDARD)
            .build();
        SeatOption seatOption2 = SeatOption.builder()
            .id(RANDOM_SEAT_OPTION_ID_2L)
            .price(200_000L)
            .seatClass(SeatClass.SUPERIOR)
            .build();
        SeatOption seatOption3 = SeatOption.builder()
            .id(RANDOM_SEAT_OPTION_ID_3L)
            .price(300_000L)
            .seatClass(SeatClass.VIP)
            .build();

        Seat seat1 = Seat.builder()
            .id(RANDOM_SEAT_ID_1L)
            .seatNumber(RANDOM_SEAT_NUMBER_1)
            .seatStatus(SeatStatus.ON_HOLD)
            .seatOption(seatOption1)
            .concertId(RANDOM_CONCERT_ID_1L)
            .build();
        Seat seat2 = Seat.builder()
            .id(RANDOM_SEAT_ID_2L)
            .seatNumber(RANDOM_SEAT_NUMBER_2)
            .seatStatus(SeatStatus.ON_HOLD)
            .seatOption(seatOption2)
            .concertId(RANDOM_CONCERT_ID_1L)
            .build();
        Seat seat3 = Seat.builder()
            .id(RANDOM_SEAT_ID_3L)
            .seatNumber(RANDOM_SEAT_NUMBER_3)
            .seatStatus(SeatStatus.ON_HOLD)
            .seatOption(seatOption3)
            .concertId(RANDOM_CONCERT_ID_1L)
            .build();

        Reservation reservation = Reservation.builder()
            .id(RANDOM_RESERVATION_ID_1L)
            .status(ReservationStatus.HOLDING)
            .createdAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
            .userId(RANDOM_USER_ID_1L)
            .seats(new ArrayList<>(Arrays.asList(seat1, seat2, seat3)))
            .build();

        Payment payment = Payment.builder()
            .id(RANDOM_PAYMENT_ID_1L)
            .amount(reservation.calculatePrice())
            .createdAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
            .reservationId(reservation.id())
            .build();

        UserPoint userPoint = UserPoint.builder()
            .id(RANDOM_USER_ID_1L)
            .point(1_000_000L)
            .updatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
            .build();
        UserPoint rebalancedUserPoint = UserPoint.builder()
            .id(RANDOM_USER_ID_1L)
            .point(300_000L)
            .updatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS))
            .build();

        BDDMockito.given(reservationRepository.findById(anyLong()))
            .willReturn(reservation);
        BDDMockito.given(reservationRepository.update(any()))
            .willReturn(reservation.toCompleteStatus());

        BDDMockito.given(seatRepository.update(seat1.toOccupiedStatus(RANDOM_RESERVATION_ID_1L)))
            .willReturn(seat1.toOccupiedStatus(RANDOM_RESERVATION_ID_1L));
        BDDMockito.given(seatRepository.update(seat2.toOccupiedStatus(RANDOM_RESERVATION_ID_1L)))
            .willReturn(seat2.toOccupiedStatus(RANDOM_RESERVATION_ID_1L));
        BDDMockito.given(seatRepository.update(seat3.toOccupiedStatus(RANDOM_RESERVATION_ID_1L)))
            .willReturn(seat3.toOccupiedStatus(RANDOM_RESERVATION_ID_1L));

        BDDMockito.given(reservationAccessRepository.getByUserId(anyLong()))
            .willReturn(reservationAccess);
        BDDMockito.given(reservationAccessRepository.update(any()))
            .willReturn(reservationAccess.close());

        BDDMockito.willDoNothing().given(pointValidator)
            .validatePointGreaterThanZero(anyLong());
        BDDMockito.given(userPointRepository.selectById(anyLong()))
            .willReturn(userPoint);
        BDDMockito.willDoNothing().given(pointValidator)
            .validateSufficientPoints(anyLong(), any());
        BDDMockito.given(userPointRepository.insertOrUpdate(any()))
            .willReturn(rebalancedUserPoint);
        BDDMockito.willDoNothing().given(pointHistoryRepository).insert(any());

        BDDMockito.given(paymentRepository.save(any()))
            .willReturn(payment);

        // when
        Payment result = concertService.proceedPayment(payment);

        // then
        assertAll(
            () -> Assertions.assertThat(result.reservationId()).isEqualTo(RANDOM_RESERVATION_ID_1L),
            () -> Assertions.assertThat(result.amount()).isEqualTo(600_000L)
        );
    }
}
