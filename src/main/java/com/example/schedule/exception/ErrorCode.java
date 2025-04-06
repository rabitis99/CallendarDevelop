package com.example.schedule.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_ID("잘못된 아이디입니다",401,HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD("잘못된 비밀번호입니다.",401,HttpStatus.BAD_REQUEST),
    INVALID_EMAIL("잘못된 이메일입니다.",401,HttpStatus.BAD_REQUEST),

    ALREADY_SAVED_EMAIL("중복된 아이디입니다.",401,HttpStatus.BAD_REQUEST),

    UNAUTHORIZED_ID("권한이 없는 아이디입니다,",401,HttpStatus.UNAUTHORIZED),

    NOT_NULL_AND_NOT("null 이나 빈칸이 들어갈 수 없습니다.",401,HttpStatus.BAD_REQUEST);

    private final String message;
    private final int status;
    private final HttpStatus httpStatus;

}
