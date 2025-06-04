package com.pratice.java.adapter.input.rest.cliente.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ClienteEntityWrapper(
        String nomeCliente,
        String cdControleCpfCnpjFormatado,
        LocalDate dataNascimento,
        BigDecimal rendaMensal) {
}
