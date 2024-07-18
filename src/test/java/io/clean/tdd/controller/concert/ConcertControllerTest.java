package io.clean.tdd.controller.concert;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.clean.tdd.controller.concert.request.PaymentRequest;
import io.clean.tdd.controller.concert.request.ReservationRequest;
import io.clean.tdd.domain.concert.*;
import io.clean.tdd.domain.concert.model.Concert;
import io.clean.tdd.domain.concert.model.Payment;
import io.clean.tdd.domain.concert.model.Reservation;
import io.clean.tdd.domain.concert.model.Seat;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConcertController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ConcertControllerTest {

    private static final long RANDOM_ID_999L = 999L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ConcertService concertService;

    @Test
    void 특정_콘서트에_대해_예약_가능한_날짜_목록을_조회할_수_있다() throws Exception {
        // given
        BDDMockito.given(concertService.searchConcertsByConcertDetailId(anyLong()))
                .willReturn(new ArrayList<>(Arrays.asList(Concert.builder().build())));

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/concert_details/{id}", RANDOM_ID_999L))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void 대기열에서_사용자_상태가_활성_상태일시_특정_콘서트_상세정보_ID를_입력받아_해당_콘서트의_예약_가능한_좌석_목록을_조회할_수_있다() throws Exception {
        // given
        BDDMockito.given(concertService.searchSeatsByConcertId(anyLong()))
            .willReturn(new ArrayList<>(Arrays.asList(Seat.builder().build())));

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/concerts/{id}", RANDOM_ID_999L))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void 특정_콘서트의_정보와_특정_좌석_정보를_입력받아_좌석을_예약_처리할_수_있다() throws Exception {
        // given
        BDDMockito.given(concertService.reserveSeats(any()))
                .willReturn(Reservation.builder().build());

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/concerts/{id}/reservation", RANDOM_ID_999L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ReservationRequest.builder()
                    .userId(RANDOM_ID_999L)
                    .seats(new ArrayList<>(Arrays.asList(Seat.builder().build())))
                    .build())))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void 대기열에서_사용자_상태가_활성_상태일시_예약한_좌석을_결제_처리하고_결제_내역을_반환할_수_있다() throws Exception {
        // given
        BDDMockito.given(concertService.proceedPayment(any()))
            .willReturn(Payment.builder().build());

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/reservations/{id}/payment", RANDOM_ID_999L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PaymentRequest
                    .builder()
                    .build())))
            .andDo(print())
            .andExpect(status().isOk());
    }
}
