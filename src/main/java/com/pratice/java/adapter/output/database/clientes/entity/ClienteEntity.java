package com.pratice.java.adapter.output.database.clientes.entity;

import com.pratice.java.adapter.output.database.endereco.entity.EnderecoEntity;
import com.pratice.java.domain.utils.Formatter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "TCLIENTE")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CDIDCLI")
    private Long cdIdentificadorCliente;

    @Column(name = "NMCLI")
    private String nomeCliente;

    @Column(name = "CCPFCNPJ")
    private String cdCpfCnpj;

    @Column(name = "CCTRLCPFCNPJ")
    private BigDecimal cdControleCpfCnpj;

    @Transient
    private String cdControleCpfCnpjFormatado;

    @Column(name = "DTNASC")
    private LocalDate dataNascimento;

    @Column(name = "REMEN")
    private BigDecimal rendaMensal;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnderecoEntity> enderecos;

    @PostLoad
    private void formatarControleCpfCnpj() {
        if (cdCpfCnpj != null && cdControleCpfCnpj != null) {
            String cpfCnpj = cdCpfCnpj + cdControleCpfCnpj.toBigInteger().toString();
            this.cdControleCpfCnpjFormatado = Formatter.formatarCpfCnpj(cpfCnpj);
        }
    }
}


