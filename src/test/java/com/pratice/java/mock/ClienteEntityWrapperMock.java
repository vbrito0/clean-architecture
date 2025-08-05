package com.pratice.java.mock;

import com.pratice.java.adapter.input.rest.cliente.dto.ClienteEntityWrapper;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class ClienteEntityWrapperMock {

    public static ClienteEntityWrapper criarClienteEntityWrapper() {
        return ClienteEntityWrapper.builder()
                .id(1L)
                .nomeCliente("Victor")
                .cdControleCpfCnpjFormatado("55557221840")
                .dataNascimento(LocalDate.parse("2002-09-27"))
                .rendaMensal(BigDecimal.valueOf(150000))
                .build();
    }

    public static List<ClienteEntityWrapper> criarListaClienteEntityWrapper() {
        return List.of(new ClienteEntityWrapper(1L, "Victor", "55557221840", LocalDate.parse("2002-09-27"), BigDecimal.valueOf(150000), null),
                new ClienteEntityWrapper(2L, "Rafaela", "55557221840", LocalDate.parse("2005-02-19"), BigDecimal.valueOf(150000), null));
    }
}
