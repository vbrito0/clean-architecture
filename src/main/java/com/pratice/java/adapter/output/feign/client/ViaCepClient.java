package com.pratice.java.adapter.output.feign.client;

import com.pratice.java.adapter.input.rest.endereco.dto.response.ViaCepResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viaCepClient", url = "${url.viacep}")
public interface ViaCepClient {

    @GetMapping("/{cep}/json/")
    @CircuitBreaker(name = "viaCep", fallbackMethod = "buscarEnderecoPorCep")
    ViaCepResponse buscarEnderecoPorCep(@PathVariable("cep") String cep);

}
