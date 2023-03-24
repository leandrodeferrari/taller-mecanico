package com.besysoft.bootcamp.dto.mapper;

import com.besysoft.bootcamp.domain.entity.ManoDeObra;
import com.besysoft.bootcamp.domain.entity.Repuesto;
import com.besysoft.bootcamp.dto.response.ReparacionOutDto;

import java.time.LocalDateTime;
import java.util.List;

public interface IReparacionMapper {

    ReparacionOutDto mapToDto(ManoDeObra manoDeObra,
                              List<Repuesto> repuestos, LocalDateTime fechaFinDeReparacion);

}
