package com.pratice.java.adapter.input.rest.cliente.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record ClienteRequest(
        @NotBlank(message = "O campo nome é obrigatório")
        String nome,
        @CPF(message = "CPF INVÁLIDO")
        @NotBlank(message = "O campo cpf é obrigatório")
        String cpf,
        @NotNull(message = "O campo dataNascimento é obrigatório")
        LocalDate dataNascimento,
        @NotNull(message = "O campo rendaMensal é obrigatório")
        BigDecimal rendaMensal) {
}
