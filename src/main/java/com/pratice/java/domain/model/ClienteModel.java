package com.pratice.java.domain.model;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record ClienteModel(
        String nome,
        String cpf,
        LocalDate dataNascimento,
        BigDecimal rendaMensal) {
}
