package com.besysoft.bootcamp.dto.mapper;

import com.besysoft.bootcamp.domain.entity.Mecanico;
import com.besysoft.bootcamp.dto.request.MecanicoInDto;
import com.besysoft.bootcamp.dto.response.MecanicoOutDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IMecanicoMapper {

    @Mappings({
            @Mapping(target = "domicilio.calle", source = "calle"),
            @Mapping(target = "domicilio.codigoPostal", source = "codigoPostal"),
            @Mapping(target = "domicilio.piso", source = "piso"),
            @Mapping(target = "domicilio.numero", source = "numero"),
            @Mapping(target = "domicilio.localidad", source = "localidad"),
            @Mapping(target = "domicilio.departamento", source = "departamento")
    })
    Mecanico mapToEntity(MecanicoInDto dto);

    MecanicoOutDto mapToDto(Mecanico mecanico);

}
