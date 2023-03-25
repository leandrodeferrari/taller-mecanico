package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.DetalleOrdenDeTrabajo;
import com.besysoft.bootcamp.repository.IDetalleOrdenDeTrabajoRepository;
import com.besysoft.bootcamp.service.IDetalleOrdenDeTrabajoService;
import com.besysoft.bootcamp.util.DetalleOrdenDeTrabajoTestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DetalleOrdenDeTrabajoServiceImplTest {

    @MockBean
    private IDetalleOrdenDeTrabajoRepository detalleRepository;

    @Autowired
    private IDetalleOrdenDeTrabajoService detalleService;

    @Test
    void crear_RetornaDetalleOrdenDeTrabajo() {
        //GIVEN
        DetalleOrdenDeTrabajo detalle = DetalleOrdenDeTrabajoTestUtil.generarDetalleSinId();
        DetalleOrdenDeTrabajo esperado = DetalleOrdenDeTrabajoTestUtil.generarDetalle();

        when(this.detalleRepository.save(any(DetalleOrdenDeTrabajo.class))).thenReturn(esperado);

        //WHEN
        DetalleOrdenDeTrabajo actual = this.detalleService.crear(detalle);

        //THEN
        assertEquals(esperado, actual);
        verify(this.detalleRepository).save(any(DetalleOrdenDeTrabajo.class));
    }

    @Test
    void buscarPorId_RetornaOptionalConDetalleOrdenDeTrabajo() {
        //GIVEN
        Long id = 1L;
        Optional<DetalleOrdenDeTrabajo> esperado = Optional.of(DetalleOrdenDeTrabajoTestUtil.generarDetalle());

        when(this.detalleRepository.findById(anyLong())).thenReturn(esperado);

        //WHEN
        Optional<DetalleOrdenDeTrabajo> actual = this.detalleService.buscarPorId(id);

        //THEN
        assertTrue(actual.isPresent());
        assertEquals(esperado, actual);
        verify(this.detalleRepository).findById(anyLong());
    }

    @Test
    void buscarPorId_RetornaOptionalVacio() {
        //GIVEN
        Long id = 1L;
        Optional<DetalleOrdenDeTrabajo> esperado = Optional.empty();

        when(this.detalleRepository.findById(anyLong())).thenReturn(esperado);

        //WHEN
        Optional<DetalleOrdenDeTrabajo> actual = this.detalleService.buscarPorId(id);

        //THEN
        assertTrue(actual.isEmpty());
        assertEquals(esperado, actual);
        verify(this.detalleRepository).findById(anyLong());
    }

}