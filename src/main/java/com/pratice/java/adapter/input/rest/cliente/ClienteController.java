package com.pratice.java.adapter.input.rest.cliente;

import com.pratice.java.adapter.input.rest.cliente.dto.ClienteEntityWrapper;
import com.pratice.java.adapter.input.rest.cliente.dto.request.AtualizarDadosRequest;
import com.pratice.java.adapter.input.rest.cliente.dto.request.ClienteRequest;
import com.pratice.java.adapter.input.rest.cliente.dto.response.ClienteResponse;
import com.pratice.java.adapter.input.rest.cliente.mapper.ClienteMapper;
import com.pratice.java.port.input.ClienteUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pratice.java.domain.common.ConstanteCommon.*;

@RestController
@RequestMapping(value = "/v1/cliente")
@RequiredArgsConstructor
@Validated
public class ClienteController implements ClienteSwagger {

    private final ClienteMapper clienteMapper;
    private final ClienteUseCase clienteUseCase;

    @Override
    @PostMapping
    public ResponseEntity<ClienteResponse> criarCliente(@RequestBody @Valid ClienteRequest request) {
        var clienteModel = clienteMapper.clienteRequestToModel(request);
        clienteUseCase.executar(clienteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ClienteResponse(CRIADO_COM_SUCESSO));
    }

    @Override
    @GetMapping("/{idCliente}")
    public ResponseEntity<ClienteEntityWrapper> buscarCliente(@PathVariable Long idCliente) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteUseCase.executar(idCliente));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ClienteEntityWrapper>> buscarTodosClientes() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteUseCase.executar());
    }

    @PatchMapping("/{idCliente}")
    public ResponseEntity<ClienteResponse> atualizarDados(@PathVariable Long idCliente, @RequestBody AtualizarDadosRequest request) {
        clienteUseCase.executar(idCliente, request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ClienteResponse(ATUALIZADO_COM_SUCESSO));
    }

    @Override
    @DeleteMapping("/{idCliente}")
    public ResponseEntity<ClienteResponse> excluirCliente(@PathVariable Long idCliente) {
        clienteUseCase.exclusao(idCliente);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ClienteResponse(EXCLUIDO_COM_SUCESSO));
    }
}
