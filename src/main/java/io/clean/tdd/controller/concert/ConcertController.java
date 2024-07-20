package io.clean.tdd.controller.concert;

import io.clean.tdd.controller.access.annotation.CurrentReservationAccess;
import io.clean.tdd.controller.access.annotation.ReservationAccessCheck;
import io.clean.tdd.controller.access.session.ReservationAccessSessionStorage;
import io.clean.tdd.controller.concert.request.PaymentRequest;
import io.clean.tdd.controller.concert.request.ReservationRequest;
import io.clean.tdd.controller.concert.request.SeatRequest;
import io.clean.tdd.domain.access.ReservationAccessService;
import io.clean.tdd.domain.access.model.ReservationAccess;
import io.clean.tdd.domain.common.exception.CustomException;
import io.clean.tdd.domain.common.exception.ErrorCode;
import io.clean.tdd.domain.concert.*;
import io.clean.tdd.domain.concert.model.Concert;
import io.clean.tdd.domain.payment.model.Payment;
import io.clean.tdd.domain.concert.model.Reservation;
import io.clean.tdd.domain.concert.model.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertService concertService;
    private final ReservationAccessService reservationAccessService;
    private final ReservationAccessSessionStorage reservationAccessSessionStorage;

    @GetMapping("/concert_details/{id}")
    public ResponseEntity<List<Concert>> concertsByDetailId(
        @PathVariable("id") long concertDetailId
    ) {
        return ResponseEntity
            .ok()
            .body(concertService.searchConcertsByConcertDetailId(concertDetailId));
    }

    @ReservationAccessCheck
    @GetMapping("/concerts/{id}")
    public ResponseEntity<List<Seat>> reservedSeats(
        @CurrentReservationAccess String reservationAccessTokenId,
        @PathVariable("id") long id,
        @RequestBody SeatRequest seatRequest // 추가
    ) {
        // token이 없을시 발급후 대기하도록 에러 반환
        if (reservationAccessTokenId == null) {
            ReservationAccess reservationAccess = reservationAccessService.generate(seatRequest.userId());
            reservationAccessService.save(reservationAccess);
            reservationAccessSessionStorage.storeSession(reservationAccess.tokenId());

            throw new CustomException(ErrorCode.INACCESSIBLE_RESERVATION_ERROR);
        }

        // 기한 만료시 세션 만료 처리
        try {
            reservationAccessService.checkAccessForReservation(reservationAccessTokenId);
        } catch (CustomException e) {
            if (e.getErrorCode().equals(ErrorCode.EXPIRED_TOKEN_ERROR)) {
                reservationAccessSessionStorage.discardSession();
                throw e;
            }
        }

        return ResponseEntity
            .ok()
            .body(concertService.searchSeatsByConcertId(id));
    }

    @ReservationAccessCheck
    @PostMapping("/concerts/{id}/reservation") // uri 및 id 제거 재고(필요 없어짐)
    public ResponseEntity<Reservation> reservation(
        @CurrentReservationAccess String reservationAccessTokenId,
        @PathVariable("id") long id, // 인자
        @RequestBody ReservationRequest reservationRequest
    ) {
        // token이 없을시 발급후 대기하도록 에러 반환
        if (reservationAccessTokenId == null) {
            ReservationAccess reservationAccess = reservationAccessService.generate(reservationRequest.userId());
            reservationAccessService.save(reservationAccess);
            reservationAccessSessionStorage.storeSession(reservationAccess.tokenId());

            throw new CustomException(ErrorCode.INACCESSIBLE_RESERVATION_ERROR);
        }

        // 기한 만료시 세션 만료 처리
        try {
            reservationAccessService.checkAccessForReservation(reservationAccessTokenId);
        } catch (CustomException e) {
            if (e.getErrorCode().equals(ErrorCode.EXPIRED_TOKEN_ERROR)) {
                reservationAccessSessionStorage.discardSession();
                throw e;
            }
        }

        return ResponseEntity
            .ok()
            .body(concertService.reserveSeats(reservationRequest.toModel(id)));
    }

    @ReservationAccessCheck
    @PostMapping("reservations/{id}/payment")
    public ResponseEntity<Payment> payment(
        @CurrentReservationAccess String reservationAccessTokenId,
        @PathVariable("id") long reservationId,
        @RequestBody PaymentRequest paymentRequest // 내용 수정(userId 추가)
    ) {
        // token이 없을시 발급후 대기하도록 에러 반환
        if (reservationAccessTokenId == null) {
            ReservationAccess reservationAccess = reservationAccessService.generate(paymentRequest.userId());
            reservationAccessService.save(reservationAccess);
            reservationAccessSessionStorage.storeSession(reservationAccess.tokenId());

            throw new CustomException(ErrorCode.INACCESSIBLE_RESERVATION_ERROR);
        }

        // 기한 만료시 세션 만료 처리
        try {
            ReservationAccess reservationAccess = reservationAccessService.checkAccessForPayment(reservationAccessTokenId);

            reservationAccessService.resetExpiration(reservationAccess);
        } catch (CustomException e) {
            if (e.getErrorCode().equals(ErrorCode.EXPIRED_TOKEN_ERROR)) {
                reservationAccessSessionStorage.discardSession();
                throw e;
            }
        }

        return ResponseEntity
            .ok()
            .body(concertService.proceedPayment(paymentRequest.toModel(reservationId)));
    }
}
