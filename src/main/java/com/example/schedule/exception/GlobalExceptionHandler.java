package com.example.schedule.exception;


import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorDto> handleCustomException(CustomException customException){

        ErrorDto errorDto=new ErrorDto(customException.errorCode,customException.filedErrors);

        return new ResponseEntity<>(errorDto, HttpStatus.valueOf(errorDto.status));
    }
    @ExceptionHandler(NoSuchMethodException.class)
    public ResponseEntity<ErrorDto> handleNoSuchMethodException(NoSuchMethodException noSuchMethodException){
        ErrorDto errorDto=new ErrorDto(noSuchMethodException.getMessage());
        return new ResponseEntity<>(errorDto,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorDto>handleSQLException(SQLException sqlException){
        return new ResponseEntity<>(new ErrorDto<>(sqlException.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException){
        return new ResponseEntity<>(new ErrorDto(illegalArgumentException.getLocalizedMessage()),HttpStatus.NOT_FOUND);
    }
    //@ExceptionHandler 이름 외우기
    //MethodArgumentNotValidException 랑 ConstraintViolationException 기억하자
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleConstraintViolation(ConstraintViolationException ex) {

        return new ResponseEntity<>(new ErrorDto<>(ex.getMessage()),HttpStatus.BAD_REQUEST);
    }
}
