package com.example.schedule.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    Id("잘못된 아이디입니다",401,LocalDateTime.now(),HttpStatus.BAD_REQUEST),
    Password("잘못된 비밀번호입니다.",401,LocalDateTime.now(),HttpStatus.BAD_REQUEST),
    Email("잘못된 이메일입니다.",401,LocalDateTime.now(),HttpStatus.BAD_REQUEST),
    Login("로그인을 해주세요",401,LocalDateTime.now(),HttpStatus.UNAUTHORIZED);

    private final String message;
    private final int status;
    private final LocalDateTime localDateTime;
    private final HttpStatus httpStatus;

}
