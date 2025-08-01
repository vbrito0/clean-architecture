package com.pratice.java.adapter.input.rest.cliente.dto;

import com.pratice.java.adapter.input.rest.endereco.dto.EnderecoEntityWrapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ClienteEntityWrapper(
        Long id,
        String nomeCliente,
        String cdControleCpfCnpjFormatado,
        LocalDate dataNascimento,
        BigDecimal rendaMensal,
        List<EnderecoEntityWrapper> enderecoList) {
}
