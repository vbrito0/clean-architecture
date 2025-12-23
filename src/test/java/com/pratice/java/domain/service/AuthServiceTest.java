package com.pratice.java.domain.service;

import com.pratice.java.adapter.exception.GlobalExceptionHandler;
import com.pratice.java.adapter.exception.PasswordInvalidException;
import com.pratice.java.adapter.input.rest.auth.dto.request.AuthRequest;
import com.pratice.java.adapter.input.rest.auth.mapper.AuthMapper;
import com.pratice.java.adapter.output.database.usuarios.entity.Usuario;
import com.pratice.java.mock.AuthRequestMock;
import com.pratice.java.mock.UsuarioMock;
import com.pratice.java.port.output.TokenGeneratorPort;
import com.pratice.java.port.output.UsuarioPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Import(GlobalExceptionHandler.class)
class AuthServiceTest {

    @Mock
    private UsuarioPort usuarioPort;

    @Mock
    private AuthMapper authMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenGeneratorPort tokenGeneratorPort;

    @InjectMocks
    private AuthService service;

    @DisplayName("Retornar sucesso quando for registrar usuário")
    @Test
    void retornarSucesso_quando_registrarUsuario() {
        AuthRequest request = AuthRequestMock.criarAuthRequestMock();
        Usuario usuario = UsuarioMock.criarUsuarioMock();
        String senhaEncode = "xxxxxxxxxx";

        when(passwordEncoder.encode(request.senha())).thenReturn(senhaEncode);
        when(authMapper.toEntity(request.email(), senhaEncode)).thenReturn(usuario);

        service.registrar(request);

        verify(passwordEncoder).encode(request.senha());
        verify(authMapper).toEntity(request.email(), senhaEncode);
        verify(usuarioPort).salvarUsuario(usuario);
    }

    @DisplayName("Retornar sucesso quando for realizar login usuário")
    @Test
    void retornarSucesso_quando_loginUsuario() {
        String username = "teste@gmail.com";
        String senha = "teste#123";
        Usuario usuario = UsuarioMock.criarUsuarioMock();

        when(usuarioPort.buscarUsuario(username)).thenReturn(usuario);
        when(passwordEncoder.matches(senha, usuario.getSenha())).thenReturn(true);
        when(tokenGeneratorPort.gerarToken(usuario)).thenReturn("TOKEN");

        service.login(username, senha);

        verify(usuarioPort).buscarUsuario(username);
        verify(passwordEncoder).matches(senha, usuario.getPassword());
        verify(tokenGeneratorPort).gerarToken(usuario);
    }

    @DisplayName("Retornar não autorizado ao comparar as senhas no login usuário")
    @Test
    void retornarUnauthorized_quando_loginUsuario() {
        String username = "teste@gmail.com";
        String senha = "xxxxxxx";
        Usuario usuario = UsuarioMock.criarUsuarioMock();

        when(usuarioPort.buscarUsuario(username)).thenReturn(usuario);
        when(passwordEncoder.matches(senha, usuario.getSenha())).thenReturn(false);

        Assertions.assertThrows(PasswordInvalidException.class, () -> {
            service.login(username, senha);
        });

        verify(usuarioPort).buscarUsuario(username);
        verify(passwordEncoder).matches(senha, usuario.getSenha());
    }
}
