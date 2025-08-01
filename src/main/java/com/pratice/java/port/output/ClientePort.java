package com.pratice.java.port.output;

import com.pratice.java.adapter.output.database.clientes.entity.ClienteEntity;

import java.util.List;

public interface ClientePort {
    void salvarCliente(ClienteEntity clienteEntity);
    ClienteEntity buscarCliente(Long idCliente);
    List<ClienteEntity> buscarTodosClientes();
    void excluirCliente(Long idCliente);
    ClienteEntity buscarCliente(String cpf);

}
