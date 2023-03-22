package com.besysoft.bootcamp.dto.mapper;

import com.besysoft.bootcamp.domain.entity.Mecanico;
import com.besysoft.bootcamp.dto.response.MecanicoOutDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMecanicoMapper {

    MecanicoOutDto mapToDto(Mecanico mecanico);

}
