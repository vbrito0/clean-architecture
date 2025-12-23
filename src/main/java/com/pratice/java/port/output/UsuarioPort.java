package com.pratice.java.port.output;

import com.pratice.java.adapter.output.database.usuarios.entity.Usuario;

public interface UsuarioPort {
    void salvarUsuario(Usuario usuario);
    Usuario buscarUsuario(String username);
}
