package com.pratice.java.mock;

import com.pratice.java.domain.model.ClienteModel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
public class ClienteModelMock {

    public static ClienteModel criarClienteModel() {
        return new ClienteModel("Victor", "55557221840", LocalDate.parse("2002-09-27"), BigDecimal.valueOf(150000));
    }
}
