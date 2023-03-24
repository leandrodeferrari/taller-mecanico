package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.DetalleOrdenDeTrabajo;
import com.besysoft.bootcamp.repository.IDetalleOrdenDeTrabajoRepository;
import com.besysoft.bootcamp.service.IDetalleOrdenDeTrabajoService;
import com.besysoft.bootcamp.util.ValidacionGeneralUtil;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
public class DetalleOrdenDeTrabajoServiceImpl implements IDetalleOrdenDeTrabajoService {

    private final IDetalleOrdenDeTrabajoRepository detalleRepository;

    public DetalleOrdenDeTrabajoServiceImpl(IDetalleOrdenDeTrabajoRepository detalleRepository) {
        this.detalleRepository = detalleRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public DetalleOrdenDeTrabajo crear(DetalleOrdenDeTrabajo detalleOrdenDeTrabajo) {
        return this.detalleRepository.save(detalleOrdenDeTrabajo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DetalleOrdenDeTrabajo> buscarPorId(Long id) {
        ValidacionGeneralUtil.validarId(id);
        return this.detalleRepository.findById(id);
    }

}
