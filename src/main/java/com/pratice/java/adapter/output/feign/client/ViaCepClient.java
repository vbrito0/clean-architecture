package com.pratice.java.adapter.output.feign.client;

import com.pratice.java.adapter.exception.CepInvalidoException;
import com.pratice.java.adapter.input.rest.endereco.response.ViaCepResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viaCepClient", url = "https://viacep.com.br/ws")
public interface ViaCepClient {

    @GetMapping("/{cep}/json/")
    @CircuitBreaker(name = "viaCep", fallbackMethod = "fallbackBuscarEnderecoPorCep")
    ViaCepResponse buscarEnderecoPorCep(@PathVariable("cep") String cep);

    default ViaCepResponse fallbackBuscarEnderecoPorCep(String cep, Throwable t) {
        throw new CepInvalidoException("Serviço ViaCEP indisponível ou CEP inválido.");
    }
}
