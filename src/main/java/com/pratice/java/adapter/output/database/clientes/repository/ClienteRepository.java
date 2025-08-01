package com.pratice.java.adapter.output.database.clientes.repository;

import com.pratice.java.adapter.output.database.clientes.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    @Query("SELECT c FROM ClienteEntity c WHERE c.cdCpfCnpj = :parteBase AND c.cdControleCpfCnpj = :controle")
    Optional<ClienteEntity> findByCpfCnpjAndControle(@Param("parteBase") String parteBase,
                                                     @Param("controle") BigDecimal controle);

}
