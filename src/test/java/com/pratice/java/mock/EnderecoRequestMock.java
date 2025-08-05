package com.pratice.java.mock;

import com.pratice.java.adapter.input.rest.endereco.request.EnderecoRequest;

public class EnderecoRequestMock {

    public static EnderecoRequest criarEnderecoRequest() {
        return EnderecoRequest.builder()
                .cep("08257100")
                .logradouro("Rua Luz do Sol")
                .numero(115)
                .bairro("Conjunto Residencial José Bonifácio")
                .estado("São Paulo")
                .cidade("São Paulo")
                .build();
    }
}
