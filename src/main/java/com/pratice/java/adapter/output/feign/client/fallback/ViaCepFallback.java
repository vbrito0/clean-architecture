package com.pratice.java.adapter.output.feign.client.fallback;

import com.pratice.java.adapter.exception.CepInvalidoException;
import com.pratice.java.adapter.input.rest.endereco.dto.response.ViaCepResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;


@Slf4j
@RequiredArgsConstructor
public class ViaCepFallback {

    public ViaCepResponse buscarEnderecoPorCep(String cep, Throwable t) {
        log.error("Fallback ativado para o CEP: {}. Erro: {}", cep, t.getMessage());
        throw new CepInvalidoException(
                SERVICE_UNAVAILABLE,
                "CEP inválido ou serviço ViaCEP indisponível."
        );
    }
}
