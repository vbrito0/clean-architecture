package com.pratice.java.adapter.output.feign;

import com.pratice.java.adapter.exception.CepInvalidoException;
import com.pratice.java.adapter.input.rest.endereco.dto.response.ViaCepResponse;
import com.pratice.java.adapter.output.feign.client.ViaCepClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ViaCepPersistence implements ViaCepClient {

    private static final Logger log = LoggerFactory.getLogger(ViaCepPersistence.class);

    @Override
    public ViaCepResponse buscarEnderecoPorCep(String cep) {
        log.info("Fallback ativado para o ViaCEP");
        throw new CepInvalidoException("CEP inválido ou serviço ViaCEP indisponível.");
    }
}
