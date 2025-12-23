package com.pratice.java.domain.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pratice.java.adapter.output.database.usuarios.entity.Usuario;
import com.pratice.java.port.output.TokenGeneratorPort;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class JwtTokenUtils implements TokenGeneratorPort {

    private static final String SECRET_KEY = "4Z^XrroxR@dWxqf$mTTKwW$!@#qGr4P";
    private static final String ISSUER = "pizzurg-api";
    private static final ZoneId ZONE_ID = ZoneId.of("America/Sao_Paulo");

    @Override
    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(creationDate())
                    .withExpiresAt(expirationDate())
                    .withSubject(usuario.getUsername())
                    .sign(algorithm);
        } catch (JWTCreationException e){
            throw new JWTCreationException("Erro ao gerar o token", e);
        }
    }

    @Override
    public String getSubjectFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Token inválido ou expirado.");
        }
    }

    @Override
    public Instant creationDate() {
        return ZonedDateTime.now(ZONE_ID).toInstant();
    }

    @Override
    public Instant expirationDate() {
        return ZonedDateTime.now(ZONE_ID).plusHours(4).toInstant();
    }
}
