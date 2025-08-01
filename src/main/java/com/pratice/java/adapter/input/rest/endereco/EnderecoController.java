package com.pratice.java.adapter.input.rest.endereco;

import com.pratice.java.adapter.input.rest.cliente.response.ClienteResponse;
import com.pratice.java.adapter.input.rest.endereco.dto.EnderecoEntityWrapper;
import com.pratice.java.adapter.input.rest.endereco.request.EnderecoRequest;
import com.pratice.java.adapter.input.rest.endereco.response.EnderecoResponse;
import com.pratice.java.domain.common.ConstanteCommon;
import com.pratice.java.port.input.EnderecoUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pratice.java.domain.common.ConstanteCommon.*;

@RestController
@RequestMapping(value = "/v1/endereco")
@RequiredArgsConstructor
@Validated
public class EnderecoController implements EnderecoSwagger{

    private final EnderecoUseCase enderecoUseCase;

    @Override
    @PostMapping("/cliente/{cpf}")
    public ResponseEntity<EnderecoResponse> adicionaEndereco(@PathVariable String cpf, @RequestBody @Valid EnderecoRequest request) {
        enderecoUseCase.executar(cpf, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new EnderecoResponse(ENDERECO_ADD_SUCESSO));
    }

    @Override
    @GetMapping("/cliente/{cpf}")
    public ResponseEntity<List<EnderecoEntityWrapper>> buscarEnderecos(@PathVariable String cpf) {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoUseCase.executar(cpf));
    }

    @Override
    @DeleteMapping("/{cep}/cliente/{cpf}")
    public ResponseEntity<EnderecoResponse> deletarEndereco(@PathVariable String cep, @PathVariable String cpf) {
        enderecoUseCase.exclusao(cep, cpf);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new EnderecoResponse(ENDERECO_EXCLUIDO_SUCESSO));
    }
}
