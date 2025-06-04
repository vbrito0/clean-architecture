package com.pratice.java.adapter.input.rest.cliente.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record ClienteRequest(
        @NotBlank(message = "O campo nome é obrigatório")
        String nome,
        @CPF(message = "CPF INVÁLIDO")
        String cpf,
        @NotBlank(message = "O campo dataNascimento é obrigatório")
        LocalDate dataNascimento,
        @NotBlank(message = "O campo rendaMensal é obrigatório")
        BigDecimal rendaMensal) {
}
