package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.OrdenDeTrabajo;
import com.besysoft.bootcamp.domain.enums.EstadoEnum;
import com.besysoft.bootcamp.dto.mapper.IOrdenDeTrabajoMapper;
import com.besysoft.bootcamp.dto.request.OrdenDeTrabajoInDto;
import com.besysoft.bootcamp.dto.response.OrdenDeTrabajoOutDto;
import com.besysoft.bootcamp.exception.OrdenDeTrabajoException;
import com.besysoft.bootcamp.repository.IOrdenDeTrabajoRepository;
import com.besysoft.bootcamp.service.IOrdenDeTrabajoService;
import com.besysoft.bootcamp.util.ValidacionGeneralUtil;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
public class OrdenDeTrabajoServiceImpl implements IOrdenDeTrabajoService {

    private final IOrdenDeTrabajoMapper ordenDeTrabajoMapper;
    private final IOrdenDeTrabajoRepository ordenDeTrabajoRepository;

    public OrdenDeTrabajoServiceImpl(IOrdenDeTrabajoMapper ordenDeTrabajoMapper,
                                     IOrdenDeTrabajoRepository ordenDeTrabajoRepository) {
        this.ordenDeTrabajoMapper = ordenDeTrabajoMapper;
        this.ordenDeTrabajoRepository = ordenDeTrabajoRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public OrdenDeTrabajoOutDto crear(OrdenDeTrabajoInDto dto) {
        OrdenDeTrabajo ordenDeTrabajo = this.ordenDeTrabajoMapper.mapToEntity(dto);
        return this.ordenDeTrabajoMapper.mapToDto(this.ordenDeTrabajoRepository.save(ordenDeTrabajo));
    }

    @Override
    @Transactional(readOnly = false)
    public OrdenDeTrabajoOutDto entregarVehiculo(Long id) {

        ValidacionGeneralUtil.validarId(id);

        Optional<OrdenDeTrabajo> optional = this.ordenDeTrabajoRepository.findById(id);

        if(optional.isEmpty()){
            throw new OrdenDeTrabajoException("No existe Orden de Trabajo con ese ID");
        }

        OrdenDeTrabajo ordenDeTrabajo = optional.get();

        if(!ordenDeTrabajo.getEstado().equals(EstadoEnum.FACTURADA.valor)){
            throw new OrdenDeTrabajoException("La Orden de Trabajo, no tiene el estado adecuado");
        }

        ordenDeTrabajo.setEstado(EstadoEnum.CERRADA.valor);

        return this.ordenDeTrabajoMapper.mapToDto(ordenDeTrabajo);

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrdenDeTrabajo> buscarPorId(Long id) {
        ValidacionGeneralUtil.validarId(id);
        return this.ordenDeTrabajoRepository.findById(id);
    }

}
