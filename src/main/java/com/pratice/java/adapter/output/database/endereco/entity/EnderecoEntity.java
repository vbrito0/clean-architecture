package com.pratice.java.adapter.output.database.endereco.entity;

import com.pratice.java.adapter.output.database.clientes.entity.ClienteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TENDERECO")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CDIDEND")
    private Long cdIdentificadorEndereco;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "LOGRADOURO")
    private String logradouro;

    @Column(name = "NUMERO")
    private Integer numero;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "CIDADE")
    private String cidade;

    @Column(name = "ESTADO")
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CDIDCLI", referencedColumnName = "CDIDCLI")
    private ClienteEntity cliente;
}
