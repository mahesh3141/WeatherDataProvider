package com.weatherDataProvider.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomExceptionHandler.class)
    public ResponseEntity<ErrorResponse> handlerResourceNotFoundException(CustomExceptionHandler ex){
        String message = ex.getMessage();
        ErrorResponse response = ErrorResponse.builder().message(message).success(true).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
