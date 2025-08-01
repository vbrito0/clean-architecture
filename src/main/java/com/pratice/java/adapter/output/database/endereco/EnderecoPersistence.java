package com.pratice.java.adapter.output.database.endereco;

import com.pratice.java.adapter.output.database.endereco.entity.EnderecoEntity;
import com.pratice.java.adapter.output.database.endereco.repository.EnderecoRepository;
import com.pratice.java.port.output.EnderecoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnderecoPersistence implements EnderecoPort {

    private final EnderecoRepository repository;

    @Override
    public void salvarEndereco(EnderecoEntity endereco) {
        repository.save(endereco);
    }

    @Override
    public void excluirEndereco(EnderecoEntity endereco) {
        repository.delete(endereco);
    }
}
