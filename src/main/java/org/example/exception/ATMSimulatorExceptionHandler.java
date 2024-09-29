package org.example.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class ATMSimulatorExceptionHandler {

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<BadResponse> handleException(InsufficientFundsException ex) {
        BadResponse badResponse = new BadResponse(Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(badResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BadResponse> handleException(ConstraintViolationException ex) {
        BadResponse badResponse = new BadResponse(Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(badResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BadResponse> handleAllExceptions(Exception ex) {
        BadResponse badResponse = new BadResponse(Collections.singletonList("SERVER ERROR"));
        return new ResponseEntity<>(badResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
