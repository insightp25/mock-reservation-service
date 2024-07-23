package io.clean.tdd.domain.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    CONCERT_NOT_FOUND_ERROR("404", "해당 콘서트가 존재하지 않습니다"),
    CONCERT_UNAVAILABLE_ERROR("409", "본 콘서트는 신청이 마감되었습니다"),
    ACCESS_TOKEN_NOT_FOUND("409", "접근을 위한 대기 토큰이 존재하지 않습니다"),
    INACCESSIBLE_RESERVATION_ERROR("409", "예약 접근 권한이 없습니다"),
    EXPIRED_TOKEN_ERROR("409", "토큰 기한이 만료되었습니다"),
    GENERAL_ERROR("500", "서버 오류가 발생했습니다");

    private final String code;
    private final String message;
}
