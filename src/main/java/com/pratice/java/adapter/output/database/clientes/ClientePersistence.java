package com.pratice.java.adapter.output.database.clientes;

import com.pratice.java.adapter.exception.NotFoundException;
import com.pratice.java.adapter.output.database.clientes.entity.ClienteEntity;
import com.pratice.java.adapter.output.database.clientes.repository.ClienteRepository;
import com.pratice.java.domain.utils.Formatter;
import com.pratice.java.port.output.ClientePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

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

    @Override
    public List<ClienteEntity> buscarTodosClientes() {
        return repository.findAll();
    }

    @Override
    public void excluirCliente(Long idCliente) {
        ClienteEntity entity = repository.findById(idCliente).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), MSG_NOT_FOUND));
        repository.delete(entity);
    }

    @Override
    public ClienteEntity buscarCliente(String cpf) {
        String parteBase = Formatter.extrairBase(cpf);
        BigDecimal controle = Formatter.extrairFinal(cpf);
        return repository.findByCpfCnpjAndControle(parteBase, controle).orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), MSG_NOT_FOUND));
    }
}
