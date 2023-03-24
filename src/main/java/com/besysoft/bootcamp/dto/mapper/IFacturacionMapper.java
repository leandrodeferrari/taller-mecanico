package com.besysoft.bootcamp.dto.mapper;

import com.besysoft.bootcamp.domain.entity.OrdenDeTrabajo;
import com.besysoft.bootcamp.dto.response.FacturacionOutDto;

public interface IFacturacionMapper {

    FacturacionOutDto mapToDto(OrdenDeTrabajo ordenDeTrabajo);

}
