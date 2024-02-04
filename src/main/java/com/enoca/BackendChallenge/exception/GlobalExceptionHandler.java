package com.enoca.BackendChallenge.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(GlobalException globalException) {
        ErrorResponse exceptionResponse = new ErrorResponse("apiException(custom) occured: " + globalException.getMessage());
        return new ResponseEntity<>(exceptionResponse, globalException.getHttpStatus());
    }



    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse exceptionResponse = new ErrorResponse(exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
