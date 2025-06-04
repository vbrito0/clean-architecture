package com.pratice.java.adapter.input.rest.cliente;

import com.pratice.java.adapter.input.rest.cliente.dto.ClienteEntityWrapper;
import com.pratice.java.adapter.input.rest.cliente.mapper.ClienteMapper;
import com.pratice.java.adapter.input.rest.cliente.request.ClienteRequest;
import com.pratice.java.adapter.input.rest.cliente.response.ClienteResponse;
import com.pratice.java.port.input.ClienteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/cliente")
@RequiredArgsConstructor
@Validated
public class ClienteController implements ClienteSwagger{

    private final ClienteMapper clienteMapper;
    private final ClienteUseCase clienteUseCase;

    @Override
    @PostMapping
    public ResponseEntity<ClienteResponse> criarCliente(ClienteRequest request) {
        try {
            var clienteModel = clienteMapper.clienteRequestToModel(request);
            clienteUseCase.executar(clienteModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ClienteResponse("Cliente criado com sucesso"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<ClienteEntityWrapper> buscarCliente(Long idCliente) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clienteUseCase.executar(idCliente));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
