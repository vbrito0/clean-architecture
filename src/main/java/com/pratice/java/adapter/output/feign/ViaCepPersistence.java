package com.pratice.java.adapter.output.feign;

import com.pratice.java.adapter.input.rest.endereco.dto.response.ViaCepResponse;
import com.pratice.java.adapter.output.feign.client.ViaCepClient;
import com.pratice.java.adapter.output.feign.client.fallback.ViaCepFallback;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViaCepPersistence {

    private static final Logger log = LoggerFactory.getLogger(ViaCepPersistence.class);

    private final ViaCepClient viaCepClient;
    private final ViaCepFallback viaCepFallback;

    @CircuitBreaker(name = "viaCep", fallbackMethod = "fallbackBuscarEndereco")
    public ViaCepResponse buscarEnderecoPorCep(String cep) {
        log.info("Buscando CEP: {}", cep);
        return viaCepClient.buscarEnderecoPorCep(cep);
    }

    private ViaCepResponse fallbackBuscarEndereco(String cep, Throwable t) {
        return viaCepFallback.buscarEnderecoPorCep(cep, t);
    }
}
