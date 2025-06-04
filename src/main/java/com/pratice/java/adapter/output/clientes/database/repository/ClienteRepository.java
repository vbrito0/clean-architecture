package com.pratice.java.adapter.output.clientes.database.repository;

import com.pratice.java.adapter.output.clientes.database.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
