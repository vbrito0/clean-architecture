package com.pratice.java.adapter.output.database.clientes;

import com.pratice.java.adapter.exception.NotFoundException;
import com.pratice.java.adapter.output.database.clientes.entity.ClienteEntity;
import com.pratice.java.adapter.output.database.clientes.repository.ClienteRepository;
import com.pratice.java.domain.utils.Formatter;
import com.pratice.java.mock.ClienteEntityMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

class ClientePersistenceTest {

    private ClienteRepository repository;
    private ClientePersistence persistence;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(ClienteRepository.class);
        persistence = new ClientePersistence(repository);
    }

    @DisplayName("Deve acionar o repository de salvar o cliente")
    @Test
    void deveSalvarCliente() {
        ClienteEntity cliente = ClienteEntityMock.criarClienteEntity();
        persistence.salvarCliente(cliente);
        verify(repository, times(1)).save(cliente);
    }

    @DisplayName("Deve acionar o repository de buscar o cliente pelo id")
    @Test
    void deveBuscarClientePorId() {
        ClienteEntity cliente = ClienteEntityMock.criarClienteEntity();

        when(repository.findById(1L)).thenReturn(Optional.of(cliente));

        ClienteEntity resultado = persistence.buscarCliente(1L);

        assertNotNull(resultado);
        assertEquals("Victor", resultado.getNomeCliente());
        verify(repository, times(1)).findById(1L);
    }

    @DisplayName("Deve acionar o repository de buscar o cliente e lançar exceção se não achar")
    @Test
    void deveLancarExcecaoQuandoNaoEncontrarClientePorId() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException ex = assertThrows(NotFoundException.class, () -> {
            persistence.buscarCliente(1L);
        });

        assertEquals(NOT_FOUND, ex.getStatus());
        assertEquals("Não foi possível encontrar esse cliente", ex.getMessage());
        verify(repository, times(1)).findById(1L);
    }

    @DisplayName("Deve acionar o repository de buscar todos os clientes")
    @Test
    void deveBuscarTodosClientes() {
        List<ClienteEntity> clienteEntities = ClienteEntityMock.criarListaClienteEntity();
        when(repository.findAll()).thenReturn(clienteEntities);

        List<ClienteEntity> clientes = persistence.buscarTodosClientes();

        assertEquals(2, clientes.size());
        verify(repository, times(1)).findAll();
    }

    @DisplayName("Deve acionar o repository de excluir o cliente")
    @Test
    void deveExcluirCliente() {
        ClienteEntity cliente = ClienteEntityMock.criarClienteEntity();
        when(repository.findById(1L)).thenReturn(Optional.of(cliente));

        persistence.excluirCliente(1L);

        verify(repository, times(1)).delete(cliente);
    }

    @DisplayName("Deve acionar o repository de buscar o cliente pelo cpf")
    @Test
    void deveBuscarClientePorCpf() {
        String cpf = "12345678900";
        String base = Formatter.extrairBase(cpf);
        BigDecimal controle = Formatter.extrairFinal(cpf);

        ClienteEntity cliente = ClienteEntity.builder().cdCpfCnpj(base).cdControleCpfCnpj(controle).build();

        when(repository.findByCpfCnpjAndControle(base, controle)).thenReturn(Optional.of(cliente));

        ClienteEntity resultado = persistence.buscarCliente(cpf);

        assertEquals(base, resultado.getCdCpfCnpj());
        verify(repository).findByCpfCnpjAndControle(base, controle);
    }
}
