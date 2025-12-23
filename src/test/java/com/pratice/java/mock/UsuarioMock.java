package com.pratice.java.mock;

import com.pratice.java.adapter.output.database.usuarios.entity.Usuario;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UsuarioMock {

    public static Usuario criarUsuarioMock() {
        return Usuario.builder()
                .id(1L)
                .email("teste@gmail.com")
                .senha("teste#123")
                .build();
    }
}
