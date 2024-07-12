package io.clean.tdd.controller.concert;

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
            .body(concertService.searchByConcertDetailId(concertDetailId));
    }

    @GetMapping("/concerts/{id}")
    public ResponseEntity<List<Seat>> reservedSeats(
        @PathVariable("id") long id
    ) {
        return ResponseEntity
            .ok()
            .body(concertService.searchReservedSeatsByConcertId(id));
    }

    @PostMapping("/concerts/{id}/reservation")
    public ResponseEntity<Reservation> reservation(
        @PathVariable("id") long id,
        @RequestBody ReservationRequest reservationRequest
        ) {
        return ResponseEntity
            .ok()
            .body(concertService.reserveSeats(reservationRequest.toModel(id)));
    }

    @PostMapping("reservations/{id}/payment")
    public ResponseEntity<Payment> payment(
        @PathVariable("id") long reservationId,
        @RequestBody PaymentRequest paymentRequest
        ) {
        return ResponseEntity
            .ok()
            .body(concertService.proceedPayment(paymentRequest.toModel(reservationId)));
    }
}
