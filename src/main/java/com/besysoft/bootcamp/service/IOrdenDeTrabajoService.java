package com.besysoft.bootcamp.service;

import com.besysoft.bootcamp.dto.request.OrdenDeTrabajoInDto;
import com.besysoft.bootcamp.dto.response.OrdenDeTrabajoOutDto;

public interface IOrdenDeTrabajoService {

    OrdenDeTrabajoOutDto crear(OrdenDeTrabajoInDto dto);
    OrdenDeTrabajoOutDto entregarVehiculo(Long id);

}
