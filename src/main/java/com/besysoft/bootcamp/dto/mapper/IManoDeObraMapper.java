package com.besysoft.bootcamp.dto.mapper;

import com.besysoft.bootcamp.domain.entity.ManoDeObra;
import com.besysoft.bootcamp.dto.response.ManoDeObraOutDto;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {IMecanicoMapper.class, IOrdenDeTrabajoMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IManoDeObraMapper {

    @Mapping(target = "duracionEnHoras", dateFormat = "hh:mm:ss")
    ManoDeObraOutDto mapToDto(ManoDeObra manoDeObra);

}
