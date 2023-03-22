package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.OrdenDeTrabajo;
import com.besysoft.bootcamp.domain.enums.EstadoEnum;
import com.besysoft.bootcamp.dto.mapper.IOrdenDeTrabajoMapper;
import com.besysoft.bootcamp.dto.request.OrdenDeTrabajoInDto;
import com.besysoft.bootcamp.dto.response.OrdenDeTrabajoOutDto;
import com.besysoft.bootcamp.repository.IOrdenDeTrabajoRepository;
import com.besysoft.bootcamp.util.OrdenDeTrabajoTestUtil;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

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

    @Test
    void EntregarVehiculo_RetornarOrdenDeTrabajoOutDto() {
        //GIVEN
        OrdenDeTrabajo ordenDeTrabajo = OrdenDeTrabajoTestUtil.generarOrdenDeTrabajoFacturada();
        Long id = ordenDeTrabajo.getId();
        OrdenDeTrabajoOutDto esperado = this.ordenDeTrabajoMapper.mapToDto(ordenDeTrabajo);

        when(this.ordenDeTrabajoRepository.findById(anyLong())).thenReturn(Optional.of(ordenDeTrabajo));
        esperado.setEstado(EstadoEnum.CERRADA.valor);

        //WHEN
        OrdenDeTrabajoOutDto actual = this.ordenDeTrabajoService.entregarVehiculo(id);

        //THEN
        assertEquals(esperado, actual);
        assertEquals(EstadoEnum.CERRADA.valor, actual.getEstado());
        verify(this.ordenDeTrabajoRepository).findById(anyLong());
    }

    @Test
    void buscarPorId_RetornaOptionalConOrdenDeTrabajo() {
        //GIVEN
        Long id = 1L;
        Optional<OrdenDeTrabajo> esperado = Optional
                .of(OrdenDeTrabajoTestUtil.generarOrdenDeTrabajoFacturada());

        when(this.ordenDeTrabajoRepository.findById(anyLong())).thenReturn(esperado);

        //WHEN
        Optional<OrdenDeTrabajo> actual = this.ordenDeTrabajoService.buscarPorId(id);

        //THEN
        assertTrue(actual.isPresent());
        assertEquals(esperado, actual);
        verify(this.ordenDeTrabajoRepository).findById(anyLong());
    }

    @Test
    void buscarPorId_RetornaOptionalVacio() {
        //GIVEN
        Long id = 1L;
        Optional<OrdenDeTrabajo> esperado = Optional.empty();

        when(this.ordenDeTrabajoRepository.findById(anyLong())).thenReturn(esperado);

        //WHEN
        Optional<OrdenDeTrabajo> actual = this.ordenDeTrabajoService.buscarPorId(id);

        //THEN
        assertTrue(actual.isEmpty());
        assertEquals(esperado, actual);
        verify(this.ordenDeTrabajoRepository).findById(anyLong());
    }

}