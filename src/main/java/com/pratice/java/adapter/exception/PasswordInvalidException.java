package com.pratice.java.adapter.exception;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class PasswordInvalidException extends BusinessException {
    public PasswordInvalidException(String message) {
        super(UNAUTHORIZED, message);
    }
}