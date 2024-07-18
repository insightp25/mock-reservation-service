package io.clean.tdd.controller.concert;

import io.clean.tdd.controller.concert.annotation.CurrentReservationAccess;
import io.clean.tdd.controller.concert.annotation.ReservationAccessCheck;
import io.clean.tdd.controller.concert.request.PaymentRequest;
import io.clean.tdd.controller.concert.request.ReservationRequest;
import io.clean.tdd.domain.concert.*;
import io.clean.tdd.domain.concert.model.Concert;
import io.clean.tdd.domain.concert.model.Payment;
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

    @GetMapping("/concert_details/{id}")
    public ResponseEntity<List<Concert>> concertsByDetailId(
        @PathVariable("id") long concertDetailId
    ) {
        return ResponseEntity
            .ok()
            .body(concertService.searchConcertsByConcertDetailId(concertDetailId));
    }

//    @ReservationAccessCheck
    @GetMapping("/concerts/{id}")
    public ResponseEntity<List<Seat>> reservedSeats(
//        @CurrentReservationAccess String accessId,
        @PathVariable("id") long id
    ) {
        return ResponseEntity
            .ok()
            .body(concertService.searchSeatsByConcertId(id));
    }

//    @ReservationAccessCheck
    @PostMapping("/concerts/{id}/reservation") // uri 및 id 제거 재고(필요 없어짐)
    public ResponseEntity<Reservation> reservation(
//        @CurrentReservationAccess String accessId,
        @PathVariable("id") long id, // 인자
        @RequestBody ReservationRequest reservationRequest
    ) {
        return ResponseEntity
            .ok()
            .body(concertService.reserveSeats(reservationRequest.toModel(id)));
    }

//    @ReservationAccessCheck
    @PostMapping("reservations/{id}/payment")
    public ResponseEntity<Payment> payment(
//        @CurrentReservationAccess String accessId,
        @PathVariable("id") long reservationId,
        @RequestBody PaymentRequest paymentRequest
    ) {
        return ResponseEntity
            .ok()
            .body(concertService.proceedPayment(paymentRequest.toModel(reservationId)));
    }
}
