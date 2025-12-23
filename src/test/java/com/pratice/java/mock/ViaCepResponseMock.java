package com.pratice.java.mock;

import com.pratice.java.adapter.input.rest.endereco.dto.response.ViaCepResponse;

public class ViaCepResponseMock {

    public static ViaCepResponse criarViaCepResponse() {
        return new ViaCepResponse("08257100", "Rua Luz do Sol", null, "Conjunto Residencial José Bonifácio", "São Paulo", "SP");
    }
}
