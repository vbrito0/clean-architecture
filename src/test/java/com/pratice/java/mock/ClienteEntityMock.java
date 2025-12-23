package com.pratice.java.mock;

import com.pratice.java.adapter.output.database.clientes.entity.ClienteEntity;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class ClienteEntityMock {

    public static ClienteEntity criarClienteEntity() {
        return ClienteEntity.builder()
                .cdIdentificadorCliente(1L)
                .nomeCliente("Victor")
                .cdCpfCnpj("555572218")
                .cdControleCpfCnpj(BigDecimal.valueOf(40))
                .dataNascimento(LocalDate.parse("2002-09-27"))
                .rendaMensal(BigDecimal.valueOf(150000))
                .build();
    }

    public static List<ClienteEntity> criarListaClienteEntity() {
        return List.of(ClienteEntity.builder()
                .cdIdentificadorCliente(1L)
                .nomeCliente("Victor")
                .cdCpfCnpj("555572218")
                .cdControleCpfCnpj(BigDecimal.valueOf(40))
                .dataNascimento(LocalDate.parse("2002-09-27"))
                .rendaMensal(BigDecimal.valueOf(150000))
                .build(),
                ClienteEntity.builder()
                        .cdIdentificadorCliente(2L)
                        .nomeCliente("Rafaela")
                        .cdCpfCnpj("555572218")
                        .cdControleCpfCnpj(BigDecimal.valueOf(40))
                        .dataNascimento(LocalDate.parse("2005-02-19"))
                        .rendaMensal(BigDecimal.valueOf(150000))
                        .build());
    }
}
