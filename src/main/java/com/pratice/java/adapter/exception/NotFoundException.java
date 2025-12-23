package com.pratice.java.adapter.exception;

import java.io.Serial;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class NotFoundException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        super(NOT_FOUND, message);
    }
}
