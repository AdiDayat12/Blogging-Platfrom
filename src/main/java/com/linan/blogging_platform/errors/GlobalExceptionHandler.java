package com.linan.blogging_platform.errors;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BlogNotFound.class)
    ResponseEntity<?> blogNotFound (BlogNotFound blogNotFound) {
        return ResponseEntity.status(404).body(blogNotFound.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<?> invalidInput (MethodArgumentNotValidException e) {
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<?> invalidId (ConstraintViolationException e) {
        String message = e.getMessage();
        return ResponseEntity.status(404).body(message);
    }
}
