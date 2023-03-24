package com.besysoft.bootcamp.service;

import com.besysoft.bootcamp.domain.entity.DetalleOrdenDeTrabajo;

import java.util.Optional;

public interface IDetalleOrdenDeTrabajoService {

    DetalleOrdenDeTrabajo crear(DetalleOrdenDeTrabajo detalleOrdenDeTrabajo);
    Optional<DetalleOrdenDeTrabajo> buscarPorId(Long id);

}
