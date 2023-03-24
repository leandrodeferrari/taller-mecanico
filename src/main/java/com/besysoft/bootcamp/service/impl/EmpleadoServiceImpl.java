package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.Empleado;
import com.besysoft.bootcamp.repository.IEmpleadoRepository;
import com.besysoft.bootcamp.service.IEmpleadoService;
import com.besysoft.bootcamp.util.ValidacionGeneralUtil;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
public class EmpleadoServiceImpl implements IEmpleadoService {

    private final IEmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(IEmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Empleado> buscarPorId(Long id) {
        ValidacionGeneralUtil.validarId(id);
        return this.empleadoRepository.findById(id);
    }

}
