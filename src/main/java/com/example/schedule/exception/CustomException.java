package com.example.schedule.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException{
     ErrorCode errorCode;
     String[] filedErrors;
    public  CustomException(ErrorCode errorCode, String[] filedErrors) {
        this.errorCode=errorCode;
        this.filedErrors=filedErrors;
    }
}
