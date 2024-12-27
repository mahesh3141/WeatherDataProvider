package com.weatherDataProvider.exceptionHandler;

public class CustomExceptionHandler extends RuntimeException{
    public CustomExceptionHandler(String msg) {
        super(msg);
    }
}
