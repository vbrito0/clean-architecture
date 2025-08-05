package com.pratice.java.adapter.input.rest.cliente.dto;

import com.pratice.java.adapter.input.rest.endereco.dto.EnderecoEntityWrapper;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
public record ClienteEntityWrapper(
        Long id,
        String nomeCliente,
        String cdControleCpfCnpjFormatado,
        LocalDate dataNascimento,
        BigDecimal rendaMensal,
        List<EnderecoEntityWrapper> enderecoList) {
}
