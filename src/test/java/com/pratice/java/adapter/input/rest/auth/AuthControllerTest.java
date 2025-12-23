package com.pratice.java.adapter.input.rest.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratice.java.adapter.exception.GlobalExceptionHandler;
import com.pratice.java.adapter.input.rest.auth.dto.request.AuthRequest;
import com.pratice.java.mock.AuthRequestMock;
import com.pratice.java.port.input.UsuarioUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@Import(GlobalExceptionHandler.class)
class AuthControllerTest {

    @InjectMocks
    private AuthController controller;

    @Mock
    private UsuarioUseCase usuarioUseCase;

    private MockMvc mockMvc;
    private static ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new GlobalExceptionHandler()).build();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @DisplayName("Quando registrar um usuário deve retornar sucesso")
    @Test
    void quando_registrarUsuario_deve_retornarSucesso() throws Exception {
        AuthRequest request = AuthRequestMock.criarAuthRequestMock();

        doNothing().when(usuarioUseCase).registrar(request);

        var result = mockMvc.perform(post("/v1/auth/registrar")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Usuário registrado com sucesso"))
                .andReturn();

        System.out.println(">>> RESPOSTA MOCKMVC: " + result.getResponse().getContentAsString());
    }

    @DisplayName("Quando realizar um login de um usuário deve retornar sucesso")
    @Test
    void quando_loginUsuario_deve_retornarSucesso() throws Exception {

        when(usuarioUseCase.login("teste@gmail.com", "teste#123")).thenReturn("TOKEN");

        var result = mockMvc.perform(post("/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("username", "teste@gmail.com")
                        .param("senha", "teste#123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(">>> RESPOSTA MOCKMVC: " + result.getResponse().getContentAsString());
    }
}
