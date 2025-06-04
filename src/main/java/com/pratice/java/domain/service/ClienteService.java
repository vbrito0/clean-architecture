package com.pratice.java.domain.service;

import com.pratice.java.adapter.input.rest.cliente.dto.ClienteEntityWrapper;
import com.pratice.java.adapter.input.rest.cliente.mapper.ClienteMapper;
import com.pratice.java.domain.model.ClienteModel;
import com.pratice.java.port.input.ClienteUseCase;
import com.pratice.java.port.output.ClientePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.pratice.java.domain.common.ConstanteCommon.*;

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
}
