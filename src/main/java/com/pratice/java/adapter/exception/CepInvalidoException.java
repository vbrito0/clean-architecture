package com.pratice.java.adapter.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class CepInvalidoException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    private final HttpStatus status;
    private final String message;

    public CepInvalidoException(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.message = message;
    }

    public CepInvalidoException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
