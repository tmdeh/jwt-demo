package com.fc.jwtdemo.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {
    EXIST(HttpStatus.BAD_REQUEST, "이미 존재하는 사용자 입니다."),
    NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 사용자는 없습니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 만료됐습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 토큰입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
    APPLICATION_TYPE_ERROR(HttpStatus.BAD_REQUEST, "JSON 형식만 지원합니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "형식에 맞지 않습니다.")
    ;

    private final HttpStatus status;
    private final String errorMessage;
}
