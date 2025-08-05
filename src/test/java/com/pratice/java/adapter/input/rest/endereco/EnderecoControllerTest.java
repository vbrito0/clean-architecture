package com.pratice.java.adapter.input.rest.endereco;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pratice.java.adapter.exception.GlobalExceptionHandler;
import com.pratice.java.adapter.input.rest.endereco.dto.EnderecoEntityWrapper;
import com.pratice.java.adapter.input.rest.endereco.mapper.EnderecoMapper;
import com.pratice.java.adapter.input.rest.endereco.request.EnderecoRequest;
import com.pratice.java.mock.EnderecoEntityWrapperMock;
import com.pratice.java.mock.EnderecoRequestMock;
import com.pratice.java.port.input.ClienteUseCase;
import com.pratice.java.port.input.EnderecoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@Import(GlobalExceptionHandler.class)
class EnderecoControllerTest {

    @InjectMocks
    private EnderecoController controller;

    @Mock
    private EnderecoUseCase enderecoUseCase;

    @Mock
    private EnderecoMapper mapper;

    private MockMvc mockMvc;
    private static ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new GlobalExceptionHandler()).build();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @DisplayName("Quando adicionar um endereço ao um cliente deve retornar sucesso")
    @Test
    void quando_adicionarEndereco_deve_retornarSucesso() throws Exception {
        EnderecoRequest enderecoMock = EnderecoRequestMock.criarEnderecoRequest();
        String numeroDocumento = "55557221840";

        var result = mockMvc.perform(post("/v1/endereco/cliente/" + numeroDocumento)
                            .content(objectMapper.writeValueAsString(enderecoMock))
                            .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("O endereço foi adicionado ao cadastro do cliente com sucesso"))
                .andReturn();

        Mockito.verify(enderecoUseCase).executar(numeroDocumento, enderecoMock);

        System.out.println(">>> RESPOSTA MOCKMVC: " + result.getResponse().getContentAsString());
    }

    @DisplayName("Quando buscar um endereço do cliente deve retornar sucesso")
    @Test
    void quando_buscarEndereco_deve_retornarSucesso() throws Exception {
        List<EnderecoEntityWrapper> enderecoList = EnderecoEntityWrapperMock.criarListaEndereco();
        String numeroDocumento = "55557221840";

        Mockito.when(enderecoUseCase.executar(any(String.class))).thenReturn(enderecoList);

        var result = mockMvc.perform(get("/v1/endereco/cliente/" + numeroDocumento)
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Mockito.verify(enderecoUseCase).executar(numeroDocumento);

        System.out.println(">>> RESPOSTA MOCKMVC: " + result.getResponse().getContentAsString());
    }

    @DisplayName("Quando deletar um endereço do cliente deve retornar sucesso")
    @Test
    void quando_deletarEndereco_deve_retornarSucesso() throws Exception {
        String cep = "08257100";
        String numeroDocumento = "55557221840";

        var result = mockMvc.perform(delete("/v1/endereco/" + cep + "/cliente/" + numeroDocumento)
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.message").value("O endereço foi excluido ao cadastro do cliente com sucesso"))
                .andReturn();

        Mockito.verify(enderecoUseCase).exclusao(cep, numeroDocumento);

        System.out.println(">>> RESPOSTA MOCKMVC: " + result.getResponse().getContentAsString());
    }
}
