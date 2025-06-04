package com.pratice.java.adapter.output.clientes.database;

import com.pratice.java.adapter.exception.NotFoundException;
import com.pratice.java.adapter.output.clientes.database.entity.ClienteEntity;
import com.pratice.java.adapter.output.clientes.database.repository.ClienteRepository;
import com.pratice.java.port.output.ClientePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.pratice.java.domain.common.ConstanteCommon.MSG_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class ClientePersistence implements ClientePort {

    private final ClienteRepository repository;

    @Override
    public void salvarCliente(ClienteEntity clienteEntity) {
        repository.save(clienteEntity);
    }

    @Override
    public ClienteEntity buscarCliente(Long idCliente) {
        return repository.findById(idCliente).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), MSG_NOT_FOUND));
    }
}
