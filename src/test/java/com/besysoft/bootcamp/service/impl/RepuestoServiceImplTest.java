package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.Repuesto;
import com.besysoft.bootcamp.dto.mapper.IRepuestoMapper;
import com.besysoft.bootcamp.dto.request.RepuestoInDto;
import com.besysoft.bootcamp.dto.response.RepuestoOutDto;
import com.besysoft.bootcamp.repository.IRepuestoRepository;

import com.besysoft.bootcamp.util.RepuestoTestUtil;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RepuestoServiceImplTest {

    @MockBean
    private IRepuestoRepository repuestoRepository;

    @Autowired
    private IRepuestoMapper repuestoMapper;

    @Autowired
    private RepuestoServiceImpl repuestoService;

    @Test
    void buscarTodosPorId() {
        //GIVEN
        List<Long> ids = Arrays.asList(1L, 2L);
        List<Repuesto> esperado = RepuestoTestUtil.generarRepuestos();

        when(this.repuestoRepository.findAllById(anyList())).thenReturn(esperado);

        //WHEN
        List<Repuesto> actual = this.repuestoService.buscarTodosPorId(ids);

        //THEN
        assertFalse(actual.isEmpty());
        assertEquals(esperado, actual);
        verify(this.repuestoRepository).findAllById(anyList());
    }

    @Test
    void crear_RetornaRepuesto() {
        //GIVEN
        RepuestoInDto dto = RepuestoTestUtil.generarRepuestoInDto();
        Repuesto repuesto = this.repuestoMapper.mapToEntity(dto);
        RepuestoOutDto esperado = this.repuestoMapper.mapToDto(repuesto);

        when(this.repuestoRepository.save(any(Repuesto.class))).thenReturn(repuesto);

        //WHEN
        RepuestoOutDto actual = this.repuestoService.crear(dto);

        //THEN
        assertEquals(esperado, actual);
        verify(this.repuestoRepository).save(any(Repuesto.class));
    }

}