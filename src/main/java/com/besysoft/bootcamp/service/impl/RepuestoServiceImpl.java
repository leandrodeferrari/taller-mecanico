package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.Repuesto;
import com.besysoft.bootcamp.repository.IRepuestoRepository;
import com.besysoft.bootcamp.service.IRepuestoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
public class RepuestoServiceImpl implements IRepuestoService {

    @Autowired
    private final IRepuestoRepository repuestoRepository;

    public RepuestoServiceImpl(IRepuestoRepository repuestoRepository) {
        this.repuestoRepository = repuestoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Repuesto> buscarTodosPorId(List<Long> ids) {
        //Validar ids
        return this.repuestoRepository.findAllById(ids);
    }

}
