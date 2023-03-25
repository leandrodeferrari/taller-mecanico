package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.Empleado;
import com.besysoft.bootcamp.domain.entity.OrdenDeTrabajo;
import com.besysoft.bootcamp.domain.enums.EstadoEnum;
import com.besysoft.bootcamp.dto.mapper.IOrdenDeTrabajoMapper;
import com.besysoft.bootcamp.dto.request.FacturacionInDto;
import com.besysoft.bootcamp.dto.request.OrdenDeTrabajoInDto;
import com.besysoft.bootcamp.dto.response.OrdenDeTrabajoOutDto;
import com.besysoft.bootcamp.exception.OrdenDeTrabajoException;
import com.besysoft.bootcamp.repository.IOrdenDeTrabajoRepository;
import com.besysoft.bootcamp.service.IDetalleOrdenDeTrabajoService;
import com.besysoft.bootcamp.service.IEmpleadoService;
import com.besysoft.bootcamp.util.EmpleadoTestUtil;
import com.besysoft.bootcamp.util.FacturacionTestUtil;
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

    @MockBean
    private IDetalleOrdenDeTrabajoService detalleService;

    @MockBean
    private IEmpleadoService empleadoService;

    @Autowired
    private IOrdenDeTrabajoMapper ordenDeTrabajoMapper;

    @Autowired
    private OrdenDeTrabajoServiceImpl ordenDeTrabajoService;

    @Test
    void Crear_RetornarOrdenDeTrabajoOutDto() {
        //GIVEN
        OrdenDeTrabajoInDto dto = OrdenDeTrabajoTestUtil.generarOrdenDeTrabajoInDto();
        OrdenDeTrabajo ordenDeTrabajo = this.ordenDeTrabajoMapper.mapToEntity(dto);
        Empleado recepcionista = EmpleadoTestUtil.generarRecepcionista();
        ordenDeTrabajo.setRecepcionista(recepcionista);
        OrdenDeTrabajoOutDto esperado = this.ordenDeTrabajoMapper.mapToDto(ordenDeTrabajo);
        Optional<Empleado> optionalEmpleado = Optional.of(recepcionista);

        when(this.ordenDeTrabajoRepository.save(any(OrdenDeTrabajo.class))).thenReturn(ordenDeTrabajo);
        when(this.empleadoService.buscarPorId(anyLong())).thenReturn(optionalEmpleado);

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

    @Test
    void facturar() {
        //GIVEN
        Long administrativoId = 1L;
        Long ordenDeTrabajoId = 1L;
        FacturacionInDto dto = FacturacionTestUtil.generarFacturacionInDto();
        Optional<OrdenDeTrabajo> optionalOrdenDeTrabajo = Optional
                .of(OrdenDeTrabajoTestUtil.generarOrdenDeTrabajoFacturada());

        when(this.ordenDeTrabajoRepository.findById(anyLong())).thenReturn(optionalOrdenDeTrabajo);

        //WHEN Y THEN
        assertThrows(OrdenDeTrabajoException.class,() -> {
            this.ordenDeTrabajoService.facturar(dto, ordenDeTrabajoId, administrativoId);
        });
        verify(this.ordenDeTrabajoRepository).findById(anyLong());
        verify(this.empleadoService, never()).buscarPorId(anyLong());
        verify(this.detalleService, never()).buscarPorId(anyLong());
    }

}