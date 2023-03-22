package com.besysoft.bootcamp.service;

import com.besysoft.bootcamp.domain.entity.Vehiculo;

import java.util.Optional;

public interface IVehiculoService {

    Optional<Vehiculo> buscarPorPatente(String patente);
    Vehiculo crear(Vehiculo vehiculo);

}
