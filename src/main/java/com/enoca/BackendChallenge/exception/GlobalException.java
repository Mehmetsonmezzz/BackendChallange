package com.enoca.BackendChallenge.exception;

import lombok.*;
import org.springframework.http.HttpStatus;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class GlobalException extends RuntimeException {

   private HttpStatus httpStatus;

    public GlobalException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
