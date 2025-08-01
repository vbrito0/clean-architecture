package com.pratice.java.port.input;

import com.pratice.java.adapter.input.rest.endereco.dto.EnderecoEntityWrapper;
import com.pratice.java.adapter.input.rest.endereco.request.EnderecoRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface EnderecoUseCase {
    void executar(String cpf, @Valid EnderecoRequest request);
    List<EnderecoEntityWrapper> executar(String cpf);
    void exclusao(String cep, String cpf);
}
