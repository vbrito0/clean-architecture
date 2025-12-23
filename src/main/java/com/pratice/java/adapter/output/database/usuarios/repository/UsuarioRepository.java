package com.pratice.java.adapter.output.database.usuarios.repository;

import com.pratice.java.adapter.output.database.usuarios.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String username);
}
