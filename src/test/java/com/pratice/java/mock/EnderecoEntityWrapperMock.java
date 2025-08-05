package com.pratice.java.mock;

import com.pratice.java.adapter.input.rest.endereco.dto.EnderecoEntityWrapper;

import java.util.List;

public class EnderecoEntityWrapperMock {

    public static List<EnderecoEntityWrapper> criarListaEndereco() {
        return List.of(new EnderecoEntityWrapper(1L, "08257100", "Rua Luz do Sol",
                115, "Conjunto Residencial José Bonifácio", "São Paulo", "São Paulo"));
    }
}
