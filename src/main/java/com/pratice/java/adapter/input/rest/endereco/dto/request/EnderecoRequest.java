package com.pratice.java.adapter.input.rest.endereco.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record EnderecoRequest(@NotBlank String cep,
                              String logradouro,
                              Integer numero,
                              String bairro,
                              String cidade,
                              String estado) {
}
