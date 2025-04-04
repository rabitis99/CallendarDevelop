package com.example.schedule.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    Id("잘못된 아이디입니다",401,HttpStatus.BAD_REQUEST),
    Password("잘못된 비밀번호입니다.",401,HttpStatus.BAD_REQUEST),
    Email("잘못된 이메일입니다.",401,HttpStatus.BAD_REQUEST),
    DuplicateEmail("중복된 아이디입니다.",401,HttpStatus.BAD_REQUEST);
    private final String message;
    private final int status;
    private final HttpStatus httpStatus;

}
