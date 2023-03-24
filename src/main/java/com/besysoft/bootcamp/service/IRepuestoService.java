package com.besysoft.bootcamp.service;

import com.besysoft.bootcamp.domain.entity.Repuesto;
import com.besysoft.bootcamp.dto.request.RepuestoInDto;
import com.besysoft.bootcamp.dto.response.RepuestoOutDto;

import java.util.List;

public interface IRepuestoService {

    List<Repuesto> buscarTodosPorId(List<Long> ids);
    RepuestoOutDto crear(RepuestoInDto dto);

}
