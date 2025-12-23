package com.pratice.java.adapter.input.rest.endereco.dto.response;

public record ViaCepResponse(String cep,
                             String logradouro,
                             String complemento,
                             String bairro,
                             String localidade,
                             String uf) {
}
