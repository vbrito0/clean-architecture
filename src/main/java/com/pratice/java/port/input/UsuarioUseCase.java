package com.pratice.java.port.input;

import com.pratice.java.adapter.input.rest.auth.dto.request.AuthRequest;

public interface UsuarioUseCase {
    void registrar(AuthRequest request);
    String login(String username, String senha);
}
