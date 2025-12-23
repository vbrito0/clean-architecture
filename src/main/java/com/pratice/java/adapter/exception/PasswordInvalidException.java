package com.pratice.java.adapter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class PasswordInvalidException extends BusinessException {
    public PasswordInvalidException(Integer errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }
}