package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.Vehiculo;
import com.besysoft.bootcamp.exception.VehiculoException;
import com.besysoft.bootcamp.repository.IVehiculoRepository;
import com.besysoft.bootcamp.service.IVehiculoService;
import com.besysoft.bootcamp.util.VehiculoUtil;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
public class VehiculoServiceImpl implements IVehiculoService {

    private final IVehiculoRepository vehiculoRepository;

    public VehiculoServiceImpl(IVehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Vehiculo> buscarPorPatente(String patente) {
        VehiculoUtil.validarPatente(patente);
        return this.vehiculoRepository.findByPatente(patente);
    }

    @Override
    @Transactional(readOnly = false)
    public Vehiculo crear(Vehiculo vehiculo) {
        if(vehiculo == null){
            throw new VehiculoException("El Vehículo no puede ser nulo");
        }

        return this.vehiculoRepository.save(vehiculo);

    }

}
