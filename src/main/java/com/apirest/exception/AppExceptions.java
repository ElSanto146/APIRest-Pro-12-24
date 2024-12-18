package com.apirest.exception;


import org.springframework.http.HttpStatus;

public class AppExceptions extends RuntimeException {

    private final HttpStatus status;

    public AppExceptions(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
