package io.clean.tdd.service.concert;

import io.clean.tdd.domain.concert.PointValidator;
import io.clean.tdd.domain.concert.UserPointService;
import io.clean.tdd.domain.concert.model.PointHistory;
import io.clean.tdd.domain.concert.model.TransactionType;
import io.clean.tdd.domain.concert.model.UserPoint;
import io.clean.tdd.domain.concert.port.PointHistoryRepository;
import io.clean.tdd.domain.concert.port.UserPointRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UserPointServiceTest {

    private static final long RANDOM_USER_ID_1L = 1L;
    private static final long RANDOM_POINT_HISTORY_ID_1L = 1L;
    private static final long RANDOM_POINT_CHARGE_AMOUNT_100_000L = 100_000L;

    @InjectMocks
    private UserPointService userPointService;

    @Mock
    private UserPointRepository userPointRepository;

    @Mock
    private PointHistoryRepository pointHistoryRepository;

    @Mock
    private PointValidator pointValidator; // 이후 패키지 리팩토링(서비스와 같은 위계 부적절)

    @Test
    public void 특정_유저의_포인트_정보를_조회할_수_있다() {
        // given
        UserPoint userPoint = UserPoint.builder()
            .id(RANDOM_USER_ID_1L)
            .point(RANDOM_POINT_CHARGE_AMOUNT_100_000L)
            .build();

        BDDMockito.given(userPointRepository.selectById(anyLong()))
            .willReturn(userPoint);

        // when
        UserPoint result = userPointService.getByUserId(RANDOM_USER_ID_1L);

        // then
        assertAll(
            () -> assertThat(result.id()).isEqualTo(1L),
            () -> assertThat(result.point()).isEqualTo(100_000L)
        );
    }

    @Test
    public void 특정_유저의_포인트_히스토리를_조회할_수_있다() {
        // given
        PointHistory pointHistory1 = PointHistory.builder()
            .id(RANDOM_POINT_HISTORY_ID_1L)
            .userId(RANDOM_USER_ID_1L)
            .type(TransactionType.CHARGE)
            .amount(RANDOM_POINT_CHARGE_AMOUNT_100_000L)
            .build();
        PointHistory pointHistory2 = PointHistory.builder()
            .id(RANDOM_POINT_HISTORY_ID_1L)
            .userId(RANDOM_USER_ID_1L)
            .type(TransactionType.CHARGE)
            .amount(RANDOM_POINT_CHARGE_AMOUNT_100_000L)
            .build();

        BDDMockito.given(pointHistoryRepository.selectAllByUserId(anyLong()))
            .willReturn(new ArrayList<>(Arrays.asList(pointHistory1, pointHistory2)));

        // when
        List<PointHistory> result = userPointService.getHistoriesByUserId(RANDOM_USER_ID_1L);

        // then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void 특정_유저의_포인트를_충전할_시_합산후_포인트_정산_정보를_반환한다() {
        // given
        UserPoint initUserPoint = UserPoint.builder()
            .id(RANDOM_USER_ID_1L)
            .point(RANDOM_POINT_CHARGE_AMOUNT_100_000L)
            .build();

        BDDMockito.willDoNothing().given(pointValidator)
            .validatePointGreaterThanZero(anyLong());
        BDDMockito.willDoNothing().given(pointHistoryRepository).insert(any());

        BDDMockito.given(userPointRepository.selectById(anyLong()))
            .willReturn(initUserPoint);
        BDDMockito.given(userPointRepository.insertOrUpdate(any()))
            .willReturn(initUserPoint.rebalanceForCharge(RANDOM_POINT_CHARGE_AMOUNT_100_000L));

        // when
        UserPoint result = userPointService.charge(RANDOM_USER_ID_1L, RANDOM_POINT_CHARGE_AMOUNT_100_000L);

        // then
        assertAll(
            () -> assertThat(result.id()).isEqualTo(1L),
            () -> assertThat(result.point()).isEqualTo(200_000L)
        );
    }
}
