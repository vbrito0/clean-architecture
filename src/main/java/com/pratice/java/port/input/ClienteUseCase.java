package com.pratice.java.port.input;

import com.pratice.java.adapter.input.rest.cliente.dto.ClienteEntityWrapper;
import com.pratice.java.adapter.input.rest.cliente.request.AtualizarDadosRequest;
import com.pratice.java.domain.model.ClienteModel;

import java.util.List;

public interface ClienteUseCase {
    void executar(ClienteModel model);
    ClienteEntityWrapper executar(Long idCliente);
    List<ClienteEntityWrapper> executar();
    void exclusao(Long idCliente);
    void executar(Long idCliente, AtualizarDadosRequest request);
}
