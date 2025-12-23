package com.pratice.java.port.output;

import com.pratice.java.adapter.output.database.usuarios.entity.Usuario;

import java.time.Instant;

public interface TokenGeneratorPort {
    String gerarToken(Usuario usuario);
    String getSubjectFromToken(String token);
    Instant creationDate();
    Instant expirationDate();
}
