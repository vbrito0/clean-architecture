package com.pratice.java.adapter.output.database.endereco.repository;

import com.pratice.java.adapter.output.database.endereco.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
}
