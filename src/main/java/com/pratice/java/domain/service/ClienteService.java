package com.pratice.java.domain.service;

import com.pratice.java.adapter.input.rest.cliente.dto.ClienteEntityWrapper;
import com.pratice.java.adapter.input.rest.cliente.dto.request.AtualizarDadosRequest;
import com.pratice.java.adapter.input.rest.cliente.mapper.ClienteMapper;
import com.pratice.java.adapter.input.rest.endereco.dto.EnderecoEntityWrapper;
import com.pratice.java.adapter.input.rest.endereco.dto.request.EnderecoRequest;
import com.pratice.java.adapter.input.rest.endereco.dto.response.ViaCepResponse;
import com.pratice.java.adapter.input.rest.endereco.mapper.EnderecoMapper;
import com.pratice.java.adapter.output.database.clientes.entity.ClienteEntity;
import com.pratice.java.adapter.output.database.endereco.entity.EnderecoEntity;
import com.pratice.java.adapter.output.feign.client.ViaCepClient;
import com.pratice.java.domain.model.ClienteModel;
import com.pratice.java.port.input.ClienteUseCase;
import com.pratice.java.port.input.EnderecoUseCase;
import com.pratice.java.port.output.ClientePort;
import com.pratice.java.port.output.EnderecoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.pratice.java.domain.common.ConstanteCommon.INDEX_NOVE;
import static com.pratice.java.domain.common.ConstanteCommon.INDEX_ZERO;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ClienteService implements ClienteUseCase, EnderecoUseCase {

    private final ClienteMapper clienteMapper;
    private final EnderecoMapper enderecoMapper;
    private final ClientePort clientePort;
    private final ViaCepClient viaCepClient;
    private final EnderecoPort enderecoPort;

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

    @Override
    public void executar(String cpf, EnderecoRequest request) {
        ClienteEntity cliente = clientePort.buscarCliente(cpf);
        ViaCepResponse viaCep = viaCepClient.buscarEnderecoPorCep(request.cep());
        EnderecoEntity endereco = enderecoMapper.toEntity(request, viaCep, cliente);
        enderecoPort.salvarEndereco(endereco);
    }

    @Override
    public List<EnderecoEntityWrapper> executar(String cpf) {
        ClienteEntity cliente = clientePort.buscarCliente(cpf);
        return enderecoMapper.toWrapperList(cliente.getEnderecos());
    }

    @Override
    public void exclusao(String cep, String cpf) {
        ClienteEntity cliente = clientePort.buscarCliente(cpf);
        cliente.getEnderecos().removeIf(endereco -> endereco.getCep().equals(cep));
        clientePort.salvarCliente(cliente);
    }
}
