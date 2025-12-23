package com.pratice.java.adapter.output.database.endereco;

import com.pratice.java.adapter.output.database.endereco.entity.EnderecoEntity;
import com.pratice.java.adapter.output.database.endereco.repository.EnderecoRepository;
import com.pratice.java.mock.EnderecoEntityMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class EnderecoPersistenceTest {

    private EnderecoRepository repository;
    private EnderecoPersistence persistence;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(EnderecoRepository.class);
        persistence = new EnderecoPersistence(repository);
    }

    @DisplayName("Deve acionar o repository de salvar endereço")
    @Test
    void deveSalvarEndereco() {
        EnderecoEntity endereco = EnderecoEntityMock.criarEnderecoEntity();
        persistence.salvarEndereco(endereco);
        verify(repository, times(1)).save(endereco);
    }

    @DisplayName("Deve acrionar o repository de excluir endereço")
    @Test
    void deveExcluirEndereco() {
        EnderecoEntity endereco = EnderecoEntityMock.criarEnderecoEntity();
        persistence.excluirEndereco(endereco);
        verify(repository, times(1)).delete(endereco);
    }
}
