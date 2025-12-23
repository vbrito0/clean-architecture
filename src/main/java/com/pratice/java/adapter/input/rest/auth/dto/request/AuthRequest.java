package com.pratice.java.adapter.input.rest.auth.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record AuthRequest(

        @NotEmpty(message = "O e-mail é obrigatorio")
        String email,

        @NotEmpty(message = "A senha é obrigatoria")
        String senha) {
}
