package com.pratice.java.mock;

import com.pratice.java.adapter.input.rest.cliente.request.ClienteRequest;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
public class ClienteRequestMock {

    public static ClienteRequest criarClienteRequest() {
        return new ClienteRequest("Victor", "55557221840", LocalDate.parse("2002-09-27"), BigDecimal.valueOf(150000));
    }

    public static ClienteRequest criarClienteRequestCPFInvalido() {
        return new ClienteRequest("Victor", "12345678900", LocalDate.parse("2002-09-27"), BigDecimal.valueOf(150000));
    }
}
