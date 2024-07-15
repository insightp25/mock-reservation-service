package io.clean.tdd.service.concert;

import io.clean.tdd.domain.concert.ConcertService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ConcertServiceTest {

    @InjectMocks
    private ConcertService concertService;

    @Test
    void 특정_콘서트에_대해_예약_가능한_날짜_목록을_조회할_수_있다() {
        // given
//        BDDMockito.given().willReturn()

        // when & then
    }

    @Test
    void 대기열에서_사용자_상태가_활성_상태일시_특정_콘서트_상세정보_ID를_입력받아_해당_콘서트의_예약_가능한_좌석_목록을_조회할_수_있다() {
        // given

        // when & then
    }

    @Test
    void 특정_콘서트의_정보와_특정_좌석_정보를_입력받아_좌석을_예약_처리할_수_있다() {
        // given

        // when & then
    }

    @Test
    void 대기열에서_사용자_상태가_활성_상태일시_예약한_좌석을_결제_처리하고_결제_내역을_반환할_수_있다() {
        // given

        // when & then

    }
}
