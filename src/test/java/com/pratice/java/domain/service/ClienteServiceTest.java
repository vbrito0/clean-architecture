package com.pratice.java.domain.service;

import com.pratice.java.adapter.exception.GlobalExceptionHandler;
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
import com.pratice.java.domain.utils.Formatter;
import com.pratice.java.mock.*;
import com.pratice.java.port.output.ClientePort;
import com.pratice.java.port.output.EnderecoPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@Import(GlobalExceptionHandler.class)
class ClienteServiceTest {

    @Mock
    private ClienteMapper clienteMapper;

    @Mock
    private EnderecoMapper enderecoMapper;

    @Mock
    private ClientePort clientePort;

    @Mock
    private ViaCepClient viaCepClient;

    @Mock
    private EnderecoPort enderecoPort;

    @InjectMocks
    private ClienteService service;

    @DisplayName("Retorna sucesso quando vai salvar o cliente")
    @Test
    void retornaSucesso_quandoSalvarCliente() {
        ClienteModel clienteModel = ClienteModelMock.criarClienteModel();
        ClienteEntity clienteEntity = ClienteEntityMock.criarClienteEntity();

        String parteCpfInicial = Formatter.extrairBase(clienteModel.cpf());
        BigDecimal parteCpfFinal = Formatter.extrairFinal(clienteModel.cpf());

        Mockito.when(clienteMapper.clienteModelToEntity(clienteModel, parteCpfInicial, parteCpfFinal)).thenReturn(clienteEntity);

        service.executar(clienteModel);

        Mockito.verify(clienteMapper).clienteModelToEntity(clienteModel, parteCpfInicial, parteCpfFinal);
        Mockito.verify(clientePort).salvarCliente(clienteEntity);
    }

    @DisplayName("Retorna sucesso ao buscar um cliente")
    @Test
    void retornaSucesso_quandoBuscaCliente() {
        Long idCliente = 1L;
        ClienteEntity clienteEntity = ClienteEntityMock.criarClienteEntity();
        ClienteEntityWrapper clienteEntityWrapper = ClienteEntityWrapperMock.criarClienteEntityWrapper();

        Mockito.when(clientePort.buscarCliente(idCliente)).thenReturn(clienteEntity);
        Mockito.when(clienteMapper.clienteEntityToEntityWrapper(any())).thenReturn(clienteEntityWrapper);

        service.executar(idCliente);

        Mockito.verify(clientePort).buscarCliente(idCliente);
        Mockito.verify(clienteMapper).clienteEntityToEntityWrapper(clienteEntity);

        Assertions.assertEquals(idCliente, clienteEntity.getCdIdentificadorCliente());
    }

    @DisplayName("Retorna sucesso ao buscar uma lista de clientes")
    @Test
    void retornaSucesso_quandoBuscaListaCliente() {
        List<ClienteEntity> clienteEntityList = ClienteEntityMock.criarListaClienteEntity();
        List<ClienteEntityWrapper> clienteEntityWrapperList = ClienteEntityWrapperMock.criarListaClienteEntityWrapper();

        Mockito.when(clientePort.buscarTodosClientes()).thenReturn(clienteEntityList);
        Mockito.when(clienteMapper.ListEntitiesToListEntityWrapper(clienteEntityList)).thenReturn(clienteEntityWrapperList);

        service.executar();

        Mockito.verify(clientePort).buscarTodosClientes();
        Mockito.verify(clienteMapper).ListEntitiesToListEntityWrapper(clienteEntityList);

        Assertions.assertEquals(clienteEntityList.size(), clienteEntityWrapperList.size());
    }

    @DisplayName("Retorna sucesso ao exluir cliente")
    @Test
    void retornaSucesso_quandoExcluiCliente() {
        Long idCliente = 1L;

        service.exclusao(idCliente);

        Mockito.verify(clientePort).excluirCliente(idCliente);
    }

    @DisplayName("Retorna sucesso ao alterar dados do cliente")
    @Test
    void retornaSucesso_quandoAtualizarDadosCliente() {
        AtualizarDadosRequest atualizarDados = AtualizarDadosRequestMock.atualizarDadosRenda();
        ClienteEntity clienteEntity = ClienteEntityMock.criarClienteEntity();
        Long idCliente = 1L;

        Mockito.when(clientePort.buscarCliente(idCliente)).thenReturn(clienteEntity);

        service.executar(idCliente, atualizarDados);

        Mockito.verify(clientePort).buscarCliente(idCliente);
        Mockito.verify(clientePort).salvarCliente(clienteEntity);

        Assertions.assertNotNull(atualizarDados);
    }

    @DisplayName("Retorna sucesso ao adicionar endereço para um cliente")
    @Test
    void retornaSucesso_quandoAdicionarEndereco() {
        String numeroDocumento = "55557221840";
        EnderecoRequest enderecoRequest = EnderecoRequestMock.criarEnderecoRequest();
        ClienteEntity clienteEntity = ClienteEntityMock.criarClienteEntity();
        ViaCepResponse viaCepResponse = ViaCepResponseMock.criarViaCepResponse();
        EnderecoEntity enderecoEntity = EnderecoEntityMock.criarEnderecoEntity();

        Mockito.when(clientePort.buscarCliente(numeroDocumento)).thenReturn(clienteEntity);
        Mockito.when(viaCepClient.buscarEnderecoPorCep(enderecoRequest.cep())).thenReturn(viaCepResponse);
        Mockito.when(enderecoMapper.toEntity(enderecoRequest, viaCepResponse, clienteEntity)).thenReturn(enderecoEntity);

        service.executar(numeroDocumento, enderecoRequest);

        Mockito.verify(clientePort).buscarCliente(numeroDocumento);
        Mockito.verify(viaCepClient).buscarEnderecoPorCep(enderecoRequest.cep());
        Mockito.verify(enderecoMapper).toEntity(enderecoRequest, viaCepResponse, clienteEntity);
        Mockito.verify(enderecoPort).salvarEndereco(enderecoEntity);
    }

    @DisplayName("Retorna sucesso ao buscar a lista de endereços por esse cpf")
    @Test
    void retornaSucesso_quandoListaEnderecos() {
        String numeroDocumento = "55557221840";
        ClienteEntity cliente = ClienteEntityMock.criarClienteEntity();
        List<EnderecoEntity> enderecos = EnderecoEntityMock.criarListaEnderecosEntity();
        cliente.setEnderecos(enderecos);
        List<EnderecoEntityWrapper> enderecosWrappers = EnderecoEntityWrapperMock.criarListaEndereco();

        Mockito.when(clientePort.buscarCliente(numeroDocumento)).thenReturn(cliente);
        Mockito.when(enderecoMapper.toWrapperList(cliente.getEnderecos())).thenReturn(enderecosWrappers);

        service.executar(numeroDocumento);

        Mockito.verify(clientePort).buscarCliente(numeroDocumento);
        Mockito.verify(enderecoMapper).toWrapperList(cliente.getEnderecos());
    }

    @DisplayName("Retorna sucesso quando for excluir um endereço do cliente")
    @Test
    void retornaSucesso_quandoExcluirEndereco() {
        String numeroDocumento = "55557221840";
        ClienteEntity cliente = ClienteEntityMock.criarClienteEntity();
        List<EnderecoEntity> enderecos = EnderecoEntityMock.criarListaEnderecosEntity();
        cliente.setEnderecos(enderecos);

        Mockito.when(clientePort.buscarCliente(numeroDocumento)).thenReturn(cliente);

        service.exclusao("08257100", numeroDocumento);

        Mockito.verify(clientePort).buscarCliente(numeroDocumento);
        Mockito.verify(clientePort).salvarCliente(cliente);
    }
}
