package com.pratice.java.mock;

import com.pratice.java.adapter.output.database.endereco.entity.EnderecoEntity;

import java.util.ArrayList;
import java.util.List;

public class EnderecoEntityMock {

    public static EnderecoEntity criarEnderecoEntity() {
        return EnderecoEntity.builder()
                .cdIdentificadorEndereco(1L)
                .cep("08257100")
                .logradouro("Rua Luz do Sol")
                .numero(115)
                .bairro("Conjunto Residencial José Bonifácio")
                .cidade("São Paulo")
                .estado("São Paulo")
                .build();
    }

    public static List<EnderecoEntity> criarListaEnderecosEntity() {
        return new ArrayList<>(List.of(EnderecoEntity.builder()
                        .cdIdentificadorEndereco(1L)
                        .cep("08257100")
                        .logradouro("Rua Luz do Sol")
                        .numero(115)
                        .bairro("Conjunto Residencial José Bonifácio")
                        .cidade("São Paulo")
                        .estado("São Paulo")
                        .build(),
                EnderecoEntity.builder()
                        .cdIdentificadorEndereco(2L)
                        .cep("08473090")
                        .logradouro("Rua Conjunto Sítio Conceição")
                        .numero(660)
                        .bairro("Conjunto Habitacional Sitio Conceiçao")
                        .cidade("São Paulo")
                        .estado("São Paulo")
                        .build()));
    }
}
