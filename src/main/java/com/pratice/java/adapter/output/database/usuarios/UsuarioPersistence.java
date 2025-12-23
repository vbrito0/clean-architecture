package com.pratice.java.adapter.output.database.usuarios;

import com.pratice.java.adapter.exception.NotFoundException;
import com.pratice.java.adapter.output.database.usuarios.entity.Usuario;
import com.pratice.java.adapter.output.database.usuarios.repository.UsuarioRepository;
import com.pratice.java.port.output.UsuarioPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.pratice.java.domain.common.ConstanteCommon.MSG_NOT_FOUND_USER;

@Component
@RequiredArgsConstructor
public class UsuarioPersistence implements UsuarioPort {

    private final UsuarioRepository repository;

    @Override
    public void salvarUsuario(Usuario usuario) {
        repository.save(usuario);
    }

    @Override
    public Usuario buscarUsuario(String username) {
        return repository.findByEmail(username).orElseThrow(() -> new NotFoundException(MSG_NOT_FOUND_USER));
    }
}
