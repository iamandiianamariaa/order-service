package com.example.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<List<ErrorResponse>> handle(ConstraintViolationException e) {
        return new ResponseEntity<>(
                e.getConstraintViolations().stream()
                        .map(ex -> ErrorResponse.builder()
                                .message(ex.getMessage())
                                .code(400)
                                .build()
                        )
                        .collect(Collectors.toList()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ErrorResponse> handle(EntityNotFoundException e) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .message(String.format("%s with ID %s doesn't exist!", e.getEntityType(), e.getEntityId()))
                        .code(404)
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<ErrorResponse> handle(NoSuchElementException e) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .message(e.getMessage())
                        .code(404)
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<List<ErrorResponse>> handle(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(
                e.getBindingResult().getFieldErrors().stream()
                        .map(ex -> ErrorResponse.builder()
                                .message(ex.getDefaultMessage())
                                .code(400)
                                .build()
                        )
                        .collect(Collectors.toList()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handle(IllegalArgumentException e) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .message(e.getMessage())
                        .code(400)
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }
}

