package com.pratice.java.domain.service;

import com.pratice.java.adapter.exception.PasswordInvalidException;
import com.pratice.java.adapter.input.rest.auth.dto.request.AuthRequest;
import com.pratice.java.adapter.input.rest.auth.mapper.AuthMapper;
import com.pratice.java.adapter.output.database.usuarios.entity.Usuario;
import com.pratice.java.port.input.UsuarioUseCase;
import com.pratice.java.port.output.TokenGeneratorPort;
import com.pratice.java.port.output.UsuarioPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.pratice.java.domain.common.ConstanteCommon.MSG_PASSWORD_INVALID;

/***
 * Classe responsável por realizar a autenticação do usuário
 * PasswordEncoder faz uma criptografia da senha e depois realiza um match para verificar se a senha que foi salva é a mesma utilizada
 * JwtService realiza uma geração de um token para o usuario
 */
@Service
@RequiredArgsConstructor
public class AuthService implements UsuarioUseCase {

    private final UsuarioPort usuarioPort;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenGeneratorPort tokenGeneratorPort;

    @Override
    public void registrar(AuthRequest request) {
        usuarioPort.salvarUsuario(authMapper.toEntity(request.email(), passwordEncoder.encode(request.senha())));
    }

    /**
     *
     * @param username - Buscar dados de username no banco
     * @param senha - Senha para validar com a salva
     * @return String - Devolve um token
     */
    @Override
    public String login(String username, String senha) {
        Usuario usuario = usuarioPort.buscarUsuario(username);

        if(!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new PasswordInvalidException(MSG_PASSWORD_INVALID);
        }

        return tokenGeneratorPort.gerarToken(usuario);
    }
}
