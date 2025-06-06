package com.pratice.java.domain.service;

import com.pratice.java.adapter.input.rest.cliente.dto.ClienteEntityWrapper;
import com.pratice.java.adapter.input.rest.cliente.mapper.ClienteMapper;
import com.pratice.java.adapter.input.rest.cliente.request.AtualizarDadosRequest;
import com.pratice.java.adapter.output.clientes.database.entity.ClienteEntity;
import com.pratice.java.domain.model.ClienteModel;
import com.pratice.java.port.input.ClienteUseCase;
import com.pratice.java.port.output.ClientePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static com.pratice.java.domain.common.ConstanteCommon.*;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ClienteService implements ClienteUseCase {

    private final ClienteMapper clienteMapper;
    private final ClientePort clientePort;

    @Override
    public void executar(ClienteModel model) {
        clientePort.salvarCliente(clienteMapper.clienteModelToEntity(model,
                model.cpf().substring(INDEX_ZERO, INDEX_NOVE), new BigDecimal(model.cpf().substring(INDEX_NOVE))));
    }

    @Override
    public ClienteEntityWrapper executar(Long idCliente) {
        var entity = clientePort.buscarCliente(idCliente);
        return clienteMapper.clienteEntityToEntityWrapper(entity);
    }

    @Override
    public List<ClienteEntityWrapper> executar() {
        List<ClienteEntity> entities = clientePort.buscarTodosClientes();
        return clienteMapper.ListEntitiesToListEntityWrapper(entities);
    }

    @Override
    public void exclusao(Long idCliente) {
        clientePort.excluirCliente(idCliente);
    }

    @Override
    public void executar(Long idCliente, AtualizarDadosRequest request) {
        ClienteEntity entity = clientePort.buscarCliente(idCliente);
        if(nonNull(request.rendaMensal())) entity.setRendaMensal(request.rendaMensal());
        clientePort.salvarCliente(entity);
    }
}
