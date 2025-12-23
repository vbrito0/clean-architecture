package com.pratice.java.adapter.input.rest.auth;

import com.pratice.java.adapter.input.rest.auth.dto.request.AuthRequest;
import com.pratice.java.adapter.input.rest.auth.dto.response.AuthResponse;
import com.pratice.java.port.input.UsuarioUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.pratice.java.domain.common.ConstanteCommon.REGISTRADO_COM_SUCESSO;

@Validated
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController implements AuthSwagger{

    private final UsuarioUseCase usuarioUseCase;

    @Override
    @PostMapping("/registrar")
    public ResponseEntity<AuthResponse> registrar(@Valid @RequestBody AuthRequest request) {
        usuarioUseCase.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(REGISTRADO_COM_SUCESSO));
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String senha) {
        return ResponseEntity.ok(usuarioUseCase.login(username, senha));
    }
}
