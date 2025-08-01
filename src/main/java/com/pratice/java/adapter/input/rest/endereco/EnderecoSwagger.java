package com.pratice.java.adapter.input.rest.endereco;

import com.pratice.java.adapter.input.rest.endereco.dto.EnderecoEntityWrapper;
import com.pratice.java.adapter.input.rest.endereco.request.EnderecoRequest;
import com.pratice.java.adapter.input.rest.endereco.response.EnderecoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Endereço", description = "Operações referente a endereços")
public interface EnderecoSwagger {

    @Operation(
            operationId = "adicionaEndereco",
            summary = "Endpoint responsável pela adição de endereço para cliente",
            description = "Adiciona endereço para cliente"
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    ResponseEntity<EnderecoResponse> adicionaEndereco(@PathVariable String cpf, @RequestBody @Valid EnderecoRequest request);

    @Operation(
            operationId = "buscarEnderecos",
            summary = "Endpoint responsável pela busca de endereços do cliente",
            description = "Busca endereços do cliente"
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    ResponseEntity<List<EnderecoEntityWrapper>> buscarEnderecos(@PathVariable String cpf);

    @Operation(
            operationId = "deletarEndereco",
            summary = "Endpoint responsável pela busca de endereço pelo cpf do cliente e deletar",
            description = "Deletar endereço do cliente pelo cpf e cep"
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    ResponseEntity<EnderecoResponse> deletarEndereco(@PathVariable String cep, @PathVariable String cpf);
}
