package com.example.schedule.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ErrorDto<T> {
    String message;
    int status;
    LocalDateTime localDateTime;
    HttpStatus error;
    String[] fieldErrors ;
    public ErrorDto(ErrorCode errorCode,String[] fieldErrors){
        this.error=errorCode.getHttpStatus();
        this.fieldErrors=fieldErrors;
        this.message=errorCode.getMessage();
        this.status=errorCode.getStatus();
        this.localDateTime=errorCode.getLocalDateTime();
    }

    public ErrorDto(String message) {
        this.message=message;
        this.localDateTime=LocalDateTime.now();
    }

}
