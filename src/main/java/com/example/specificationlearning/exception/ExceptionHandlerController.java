package com.example.specificationlearning.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestRs> handleException(Exception e) {
        BadRequestRs response = BadRequestRs.builder()
                .error("Bad request")
                .timestamp(System.currentTimeMillis())
                .errorDescription(e.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BadRequestRs> handleValidationException(MethodArgumentNotValidException ex) {
        String cause = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        BadRequestRs badRequestRs = BadRequestRs.builder()
                .error("Bad request")
                .timestamp(System.currentTimeMillis())
                .errorDescription(cause)
                .build();
        return new ResponseEntity<>(badRequestRs, HttpStatus.BAD_REQUEST);
    }
}
