package com.pratice.java.adapter.input.rest.cliente.mapper;

import com.pratice.java.adapter.input.rest.cliente.dto.ClienteEntityWrapper;
import com.pratice.java.adapter.input.rest.cliente.request.ClienteRequest;
import com.pratice.java.adapter.output.clientes.database.entity.ClienteEntity;
import com.pratice.java.domain.model.ClienteModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "nome", source = "request.nome")
    @Mapping(target = "cpf", source = "request.cpf")
    @Mapping(target = "dataNascimento", source = "request.dataNascimento")
    @Mapping(target = "rendaMensal", source = "request.rendaMensal")
    ClienteModel clienteRequestToModel(ClienteRequest request);

    @Mapping(target = "cdControleCpfCnpjFormatado", ignore = true)
    @Mapping(target = "cdIdentificadorCliente", ignore = true)
    @Mapping(target = "nomeCliente", source = "model.nome")
    @Mapping(target = "cdCpfCnpj", source = "cpf")
    @Mapping(target = "cdControleCpfCnpj", source = "cpfDigito")
    @Mapping(target = "dataNascimento", source = "model.dataNascimento")
    @Mapping(target = "rendaMensal", source = "model.rendaMensal")
    ClienteEntity clienteModelToEntity(ClienteModel model, String cpf, BigDecimal cpfDigito);

    @Mapping(target = "id", source = "entity.cdIdentificadorCliente")
    @Mapping(target = "nomeCliente", source = "entity.nomeCliente")
    @Mapping(target = "cdControleCpfCnpjFormatado", source = "entity.cdControleCpfCnpjFormatado")
    @Mapping(target = "dataNascimento", source = "entity.dataNascimento")
    @Mapping(target = "rendaMensal", source = "entity.rendaMensal")
    ClienteEntityWrapper clienteEntityToEntityWrapper(ClienteEntity entity);

    List<ClienteEntityWrapper> ListEntitiesToListEntityWrapper(List<ClienteEntity> entities);
}
