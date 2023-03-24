package com.besysoft.bootcamp.dto.mapper;

import com.besysoft.bootcamp.domain.entity.OrdenDeTrabajo;
import com.besysoft.bootcamp.domain.enums.EstadoEnum;
import com.besysoft.bootcamp.dto.request.OrdenDeTrabajoInDto;
import com.besysoft.bootcamp.dto.response.OrdenDeTrabajoOutDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface IOrdenDeTrabajoMapper {

    @Mappings({
            @Mapping(target = "fechaDeIngreso", expression = "java(getFechaDeIngreso())"),
            @Mapping(target = "estado", expression = "java(getEstadoCreado())")
    })
    OrdenDeTrabajo mapToEntity(OrdenDeTrabajoInDto dto);

    @Mappings({
            @Mapping(target = "fechaDeIngreso", dateFormat = "yyyy-MM-dd"),
            @Mapping(target = "nombreRecepcionista", source = "ordenDeTrabajo.recepcionista.nombres"),
            @Mapping(target = "recepcionistaId", source = "ordenDeTrabajo.recepcionista.id")
    })
    OrdenDeTrabajoOutDto mapToDto(OrdenDeTrabajo ordenDeTrabajo);

    default LocalDateTime getFechaDeIngreso(){
        return LocalDateTime.now();
    }

    default String getEstadoCreado(){
        return EstadoEnum.CREADA.valor;
    }

}
