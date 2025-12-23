package com.pratice.java.adapter.input.rest.endereco.mapper;

import com.pratice.java.adapter.input.rest.endereco.dto.EnderecoEntityWrapper;
import com.pratice.java.adapter.input.rest.endereco.dto.request.EnderecoRequest;
import com.pratice.java.adapter.input.rest.endereco.dto.response.ViaCepResponse;
import com.pratice.java.adapter.output.database.clientes.entity.ClienteEntity;
import com.pratice.java.adapter.output.database.endereco.entity.EnderecoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    @Mapping(target = "cep", expression = "java(nonEmpty(request.cep()) ? request.cep() : viaCep.cep())")
    @Mapping(target = "logradouro", expression = "java(nonEmpty(request.logradouro()) ? request.logradouro() : viaCep.logradouro())")
    @Mapping(target = "numero", expression = "java(nonNull(request.numero()) ? request.numero() : null)")
    @Mapping(target = "bairro", expression = "java(nonEmpty(request.bairro()) ? request.bairro() : viaCep.bairro())")
    @Mapping(target = "cidade", expression = "java(nonEmpty(request.cidade()) ? request.cidade() : viaCep.localidade())")
    @Mapping(target = "estado", expression = "java(nonEmpty(request.estado()) ? request.estado() : viaCep.uf())")
    @Mapping(target = "cliente", source = "cliente")
    EnderecoEntity toEntity(EnderecoRequest request, ViaCepResponse viaCep, ClienteEntity cliente);

    List<EnderecoEntityWrapper> toWrapperList(List<EnderecoEntity> entities);

    default boolean nonEmpty(String value) {
        return value != null && !value.isBlank();
    }

    default boolean nonNull(Integer value) {
        return value != null;
    }
}
