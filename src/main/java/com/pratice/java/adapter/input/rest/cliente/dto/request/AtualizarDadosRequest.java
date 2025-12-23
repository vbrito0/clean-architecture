package com.pratice.java.adapter.input.rest.cliente.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AtualizarDadosRequest(BigDecimal rendaMensal){
}
