package com.pratice.java.adapter.input.rest.endereco.dto;

public record EnderecoEntityWrapper(Long cdIdentificadorEndereco,
                                    String cep,
                                    String logradouro,
                                    Integer numero,
                                    String bairro,
                                    String cidade,
                                    String estado) {
}
