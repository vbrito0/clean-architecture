package com.pratice.java.adapter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends BusinessException {

    public NotFoundException(Integer errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }
}
