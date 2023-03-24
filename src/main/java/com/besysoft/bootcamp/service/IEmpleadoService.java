package com.besysoft.bootcamp.service;

import com.besysoft.bootcamp.domain.entity.Empleado;

import java.util.Optional;

public interface IEmpleadoService {

    Optional<Empleado> buscarPorId(Long id);

}
