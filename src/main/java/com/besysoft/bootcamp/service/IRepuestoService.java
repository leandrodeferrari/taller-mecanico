package com.besysoft.bootcamp.service;

import com.besysoft.bootcamp.domain.entity.Repuesto;

import java.util.List;

public interface IRepuestoService {

    List<Repuesto> buscarTodosPorId(List<Long> ids);

}
