package com.besysoft.bootcamp.dto.mapper;

import com.besysoft.bootcamp.domain.entity.Repuesto;
import com.besysoft.bootcamp.dto.request.RepuestoInDto;
import com.besysoft.bootcamp.dto.response.RepuestoOutDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface IRepuestoMapper {

    @Mappings({
            @Mapping(target = "repuesto", source = "nombre"),
            @Mapping(target = "valor", expression = "java(mapToBigDecimal(dto.getValor()))")
    })
    Repuesto mapToEntity(RepuestoInDto dto);

    @Mappings({
            @Mapping(target = "nombre", source = "repuesto"),
            @Mapping(target = "valor", expression = "java(repuesto.getValor().toPlainString())")
    })
    RepuestoOutDto mapToDto(Repuesto repuesto);

    default BigDecimal mapToBigDecimal(String valor){
        return new BigDecimal(valor);
    }

}
