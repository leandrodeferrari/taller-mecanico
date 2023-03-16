package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.OrdenDeTrabajo;
import com.besysoft.bootcamp.dto.mapper.IOrdenDeTrabajoMapper;
import com.besysoft.bootcamp.dto.request.OrdenDeTrabajoInDto;
import com.besysoft.bootcamp.dto.response.OrdenDeTrabajoOutDto;
import com.besysoft.bootcamp.repository.IOrdenDeTrabajoRepository;
import com.besysoft.bootcamp.util.OrdenDeTrabajoTestUtil;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrdenDeTrabajoServiceImplTest {

    @MockBean
    private IOrdenDeTrabajoRepository ordenDeTrabajoRepository;

    @Autowired
    private IOrdenDeTrabajoMapper ordenDeTrabajoMapper;

    @Autowired
    private OrdenDeTrabajoServiceImpl ordenDeTrabajoService;

    @Test
    void Crear_RetornarOrdenDeTrabajoOutDto() {
        //GIVEN
        OrdenDeTrabajoInDto dto = OrdenDeTrabajoTestUtil.generarOrdenDeTrabajoInDto();
        OrdenDeTrabajo ordenDeTrabajo = this.ordenDeTrabajoMapper.mapToEntity(dto);
        OrdenDeTrabajoOutDto esperado = this.ordenDeTrabajoMapper.mapToDto(ordenDeTrabajo);

        when(this.ordenDeTrabajoRepository.save(any(OrdenDeTrabajo.class))).thenReturn(ordenDeTrabajo);

        //WHEN
        OrdenDeTrabajoOutDto actual = this.ordenDeTrabajoService.crear(dto);

        //THEN
        assertEquals(esperado, actual);
        verify(this.ordenDeTrabajoRepository).save(any(OrdenDeTrabajo.class));
    }

}