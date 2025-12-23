package com.pratice.java.adapter.input.rest.auth.mapper;

import com.pratice.java.adapter.output.database.usuarios.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    @Mapping(target = "id", ignore = true)
    Usuario toEntity(String email, String senha);
}
