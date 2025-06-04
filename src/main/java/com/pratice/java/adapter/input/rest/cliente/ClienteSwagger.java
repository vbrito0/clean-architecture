package com.pratice.java.adapter.input.rest.cliente;

import com.pratice.java.adapter.input.rest.cliente.dto.ClienteEntityWrapper;
import com.pratice.java.adapter.input.rest.cliente.request.ClienteRequest;
import com.pratice.java.adapter.input.rest.cliente.response.ClienteResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Clientes", description = "Operações referente a clientes")
public interface ClienteSwagger {

    @Operation(
            operationId = "criarCliente",
            summary = "Endpoint responsável pela criação de cliente",
            description = "Cria um cliente"
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Created"),
                           @ApiResponse(responseCode = "400", description = "Bad Request")})
    ResponseEntity<ClienteResponse> criarCliente(@RequestBody @Valid ClienteRequest request);

    @Operation(
            operationId = "buscarCliente",
            summary = "Endpoint responsável por buscar um cliente em especifico",
            description = "Busca um cliente"
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ok"),
                            @ApiResponse(responseCode = "404", description = "Not Found")})
    ResponseEntity<ClienteEntityWrapper> buscarCliente(Long idCliente);
}
