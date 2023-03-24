package com.besysoft.bootcamp.service;

import com.besysoft.bootcamp.domain.entity.OrdenDeTrabajo;
import com.besysoft.bootcamp.dto.request.FacturacionInDto;
import com.besysoft.bootcamp.dto.request.OrdenDeTrabajoInDto;
import com.besysoft.bootcamp.dto.response.FacturacionOutDto;
import com.besysoft.bootcamp.dto.response.OrdenDeTrabajoOutDto;

import java.util.Optional;

public interface IOrdenDeTrabajoService {

    OrdenDeTrabajoOutDto crear(OrdenDeTrabajoInDto dto);
    OrdenDeTrabajoOutDto entregarVehiculo(Long id);
    Optional<OrdenDeTrabajo> buscarPorId(Long id);
    FacturacionOutDto facturar(FacturacionInDto dto, Long ordenDeTrabajoId, Long administrativoId);

}
