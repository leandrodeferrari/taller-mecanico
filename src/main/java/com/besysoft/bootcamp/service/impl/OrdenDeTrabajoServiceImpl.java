package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.OrdenDeTrabajo;
import com.besysoft.bootcamp.dto.mapper.IOrdenDeTrabajoMapper;
import com.besysoft.bootcamp.dto.request.OrdenDeTrabajoInDto;
import com.besysoft.bootcamp.dto.response.OrdenDeTrabajoOutDto;
import com.besysoft.bootcamp.repository.IOrdenDeTrabajoRepository;
import com.besysoft.bootcamp.service.IOrdenDeTrabajoService;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public OrdenDeTrabajoOutDto crear(OrdenDeTrabajoInDto dto) {
        OrdenDeTrabajo ordenDeTrabajo = this.ordenDeTrabajoMapper.mapToEntity(dto);
        return this.ordenDeTrabajoMapper.mapToDto(this.ordenDeTrabajoRepository.save(ordenDeTrabajo));
    }

}
