package com.pratice.java.adapter.input.rest.cliente;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratice.java.adapter.exception.GlobalExceptionHandler;
import com.pratice.java.adapter.exception.NotFoundException;
import com.pratice.java.adapter.input.rest.cliente.dto.ClienteEntityWrapper;
import com.pratice.java.adapter.input.rest.cliente.dto.request.AtualizarDadosRequest;
import com.pratice.java.adapter.input.rest.cliente.dto.request.ClienteRequest;
import com.pratice.java.adapter.input.rest.cliente.mapper.ClienteMapper;
import com.pratice.java.domain.model.ClienteModel;
import com.pratice.java.mock.AtualizarDadosRequestMock;
import com.pratice.java.mock.ClienteEntityWrapperMock;
import com.pratice.java.mock.ClienteModelMock;
import com.pratice.java.mock.ClienteRequestMock;
import com.pratice.java.port.input.ClienteUseCase;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@Import(GlobalExceptionHandler.class)
class ClienteControllerTest {

    @InjectMocks
    private ClienteController controller;

    @Mock
    private ClienteUseCase clienteUseCase;

    @Mock
    private ClienteMapper clienteMapper;

    private MockMvc mockMvc;
    private static ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new GlobalExceptionHandler()).build();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @DisplayName("Quando criar um cliente deve retornar sucesso")
    @Test
    void quando_criarCliente_deve_retornarSucesso() throws Exception {
        ClienteRequest request = ClienteRequestMock.criarClienteRequest();
        ClienteModel model = ClienteModelMock.criarClienteModel();

        when(clienteMapper.clienteRequestToModel(any(ClienteRequest.class))).thenReturn(model);

        var result = mockMvc.perform(post("/v1/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Cliente criado com sucesso"))
                .andReturn();

        System.out.println(">>> RESPOSTA MOCKMVC: " + result.getResponse().getContentAsString());
    }

    @DisplayName("Quando criar um cliente sem cpf deve retornar erro de validação")
    @Test
    void quando_criarCliente_deve_retornarErroValidacao() throws Exception {
        ClienteRequest request = ClienteRequestMock.criarClienteRequestCPFInvalido();

        var result = mockMvc.perform(post("/v1/cliente")
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0].campo").value("cpf"))
                .andExpect(jsonPath("$.errors[0].message").value("CPF INVÁLIDO"))
                .andReturn();

        System.out.println(">>> RESPOSTA MOCKMVC: " + result.getResponse().getContentAsString());
    }

    @DisplayName("Quando buscar um cliente deve retornar sucesso")
    @Test
    void quando_buscarCliente_deve_retornarUmClienteComSucesso() throws Exception {
        ClienteEntityWrapper entityWrapperMock = ClienteEntityWrapperMock.criarClienteEntityWrapper();

        when(clienteUseCase.executar(1L)).thenReturn(entityWrapperMock);

        var result = mockMvc.perform(get("/v1/cliente/" + 1)
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        verify(clienteUseCase).executar(any(Long.class));

        System.out.println(">>> RESPOSTA MOCKMVC: " + result.getResponse().getContentAsString());
    }

    @DisplayName("Quando buscar um cliente e não encontrar deve retornar uma exceção")
    @Test
    void quando_buscarCliente_deve_retornarUmClienteNotFound() throws Exception {

        when(clienteUseCase.executar(0L)).thenThrow(new NotFoundException("Não foi possível encontrar esse cliente"));

        var result = mockMvc.perform(get("/v1/cliente/" + 0)
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errors[0].message").value("Não foi possível encontrar esse cliente"))
                .andExpect(jsonPath("$.errors[0].campo").value("NOT_FOUND"))
                .andReturn();

        verify(clienteUseCase).executar(any(Long.class));

        System.out.println(">>> RESPOSTA MOCKMVC: " + result.getResponse().getContentAsString());
    }

    @DisplayName("Quando buscar todos os clientes deve retornar sucesso")
    @Test
    void quando_buscarTodosClientes_deve_retornarAListaClientes() throws Exception {
        List<ClienteEntityWrapper> listaClientesMock = ClienteEntityWrapperMock.criarListaClienteEntityWrapper();

        when(clienteUseCase.executar()).thenReturn(listaClientesMock);

        var result = mockMvc.perform(get("/v1/cliente")
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        verify(clienteUseCase).executar();

        System.out.println(">>> RESPOSTA MOCKMVC: " + result.getResponse().getContentAsString());
    }

    @DisplayName("Quando atualizar dados do cliente deve retornar sucesso")
    @Test
    void quando_atualizarDados_deve_retornarClienteSucesso() throws Exception {
        AtualizarDadosRequest atualizarDados = AtualizarDadosRequestMock.atualizarDadosRenda();

        var result = mockMvc.perform(patch("/v1/cliente/" + 1L)
                        .content(objectMapper.writeValueAsString(atualizarDados))
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.message").value("Cliente atualizado com sucesso"))
                .andReturn();

        verify(clienteUseCase).executar(1L, atualizarDados);

        System.out.println(">>> RESPOSTA MOCKMVC: " + result.getResponse().getContentAsString());
    }

    @DisplayName("Quando exluir dados do cliente deve retornar sucesso")
    @Test
    void quando_excluirCliente_deve_retornarClienteSucesso() throws Exception {

        var result = mockMvc.perform(delete("/v1/cliente/" + 1L)
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.message").value("Cliente excluido com sucesso"))
                .andReturn();

        verify(clienteUseCase).exclusao(1L);

        System.out.println(">>> RESPOSTA MOCKMVC: " + result.getResponse().getContentAsString());
    }
}
