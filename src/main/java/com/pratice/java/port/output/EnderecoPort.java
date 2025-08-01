package com.pratice.java.port.output;

import com.pratice.java.adapter.output.database.endereco.entity.EnderecoEntity;

public interface EnderecoPort {
    void salvarEndereco(EnderecoEntity endereco);
    void excluirEndereco(EnderecoEntity endereco);
}
