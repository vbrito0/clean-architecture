package com.pratice.java.mock;

import com.pratice.java.adapter.input.rest.cliente.dto.request.AtualizarDadosRequest;

import java.math.BigDecimal;

public class AtualizarDadosRequestMock {

    public static AtualizarDadosRequest atualizarDadosRenda() {
        return new AtualizarDadosRequest(BigDecimal.valueOf(200000));
    }
}
