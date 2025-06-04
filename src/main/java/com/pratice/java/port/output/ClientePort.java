package com.pratice.java.port.output;

import com.pratice.java.adapter.output.clientes.database.entity.ClienteEntity;

import java.util.Optional;

public interface ClientePort {
    void salvarCliente(ClienteEntity clienteEntity);
    ClienteEntity buscarCliente(Long idCliente);
}
