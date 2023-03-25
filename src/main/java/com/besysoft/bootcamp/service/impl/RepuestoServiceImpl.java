package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.Repuesto;
import com.besysoft.bootcamp.dto.mapper.IRepuestoMapper;
import com.besysoft.bootcamp.dto.request.RepuestoInDto;
import com.besysoft.bootcamp.dto.response.RepuestoOutDto;
import com.besysoft.bootcamp.repository.IRepuestoRepository;
import com.besysoft.bootcamp.service.IRepuestoService;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
public class RepuestoServiceImpl implements IRepuestoService {

    private final IRepuestoMapper repuestoMapper;
    private final IRepuestoRepository repuestoRepository;

    public RepuestoServiceImpl(IRepuestoMapper repuestoMapper,
                               IRepuestoRepository repuestoRepository) {
        this.repuestoMapper = repuestoMapper;
        this.repuestoRepository = repuestoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Repuesto> buscarTodosPorId(List<Long> ids) {
        return this.repuestoRepository.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = false)
    public RepuestoOutDto crear(RepuestoInDto dto) {
        Repuesto repuesto = this.repuestoMapper.mapToEntity(dto);
        return this.repuestoMapper.mapToDto(this.repuestoRepository.save(repuesto));
    }

}
