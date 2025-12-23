package com.pratice.java.adapter.input.rest.auth;

import com.pratice.java.adapter.input.rest.auth.dto.request.AuthRequest;
import com.pratice.java.adapter.input.rest.auth.dto.response.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Usuarios", description = "Operações referente a criação e autenticação de usuários")
public interface AuthSwagger {

    @Operation(
            operationId = "registrar",
            summary = "Endpoint responsável pelo registro de usuário",
            description = "Cria um usuário"
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    ResponseEntity<AuthResponse> registrar(@Valid @RequestBody AuthRequest request);

    @Operation(
            operationId = "login",
            summary = "Endpoint responsável pelo login de usuário",
            description = "Loga um usuário"
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    ResponseEntity<String> login(@RequestParam String username, @RequestParam String senha);
}
