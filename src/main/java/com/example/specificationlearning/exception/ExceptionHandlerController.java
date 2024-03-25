package com.example.specificationlearning.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundRs> handleException(Exception e) {
        NotFoundRs notFoundRs = NotFoundRs.builder()
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(notFoundRs, HttpStatus.NOT_FOUND);
    }
}
