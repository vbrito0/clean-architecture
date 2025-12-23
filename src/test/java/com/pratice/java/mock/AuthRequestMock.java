package com.pratice.java.mock;

import com.pratice.java.adapter.input.rest.auth.dto.request.AuthRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthRequestMock {

    public static AuthRequest criarAuthRequestMock() {
        return new AuthRequest("teste@gmail.com", "teste#123");
    }

}
