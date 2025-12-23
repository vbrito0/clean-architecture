package com.pratice.java.adapter.output.database.usuarios;

import com.pratice.java.adapter.output.database.usuarios.entity.Usuario;
import com.pratice.java.adapter.output.database.usuarios.repository.UsuarioRepository;
import com.pratice.java.mock.UsuarioMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UsuarioPersistenceTest {

    private UsuarioRepository repository;
    private UsuarioPersistence persistence;

    @BeforeEach
    void setup() {
        repository = Mockito.mock(UsuarioRepository.class);
        persistence = new UsuarioPersistence(repository);
    }

    @DisplayName("Deve acionar o repository de salvar usuário")
    @Test
    void deveSalvarUsuario() {
        Usuario usuario = UsuarioMock.criarUsuarioMock();
        persistence.salvarUsuario(usuario);
        verify(repository, times(1)).save(usuario);
    }

    @DisplayName("Deve acionar o repository de buscar usuário")
    @Test
    void deveBuscarUsuario() {
        Usuario usuario = UsuarioMock.criarUsuarioMock();

        when(repository.findByEmail("teste@gmail.com")).thenReturn(Optional.of(usuario));

        Usuario resultado = persistence.buscarUsuario(usuario.getEmail());

        assertNotNull(resultado);
        assertEquals("teste@gmail.com", resultado.getEmail());
        verify(repository, times(1)).findByEmail(usuario.getEmail());
    }
}
