package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.Vehiculo;
import com.besysoft.bootcamp.exception.VehiculoException;
import com.besysoft.bootcamp.repository.IVehiculoRepository;
import com.besysoft.bootcamp.util.VehiculoTestUtil;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VehiculoServiceImplTest {

    @MockBean
    private IVehiculoRepository vehiculoRepository;

    @Autowired
    private VehiculoServiceImpl vehiculoService;

    @Test
    void existePorPatente_RetornaVerdadero() {
        //GIVEN
        String patente = "ABC123";

        when(this.vehiculoRepository.existsByPatente(anyString())).thenReturn(true);

        //WHEN
        boolean existePorPatente = this.vehiculoService.existePorPatente(patente);

        //THEN
        assertTrue(existePorPatente);
        verify(this.vehiculoRepository).existsByPatente(anyString());
    }

    @Test
    void existePorPatente_RetornaFalso() {
        //GIVEN
        String patente = "ABC123";

        when(this.vehiculoRepository.existsByPatente(anyString())).thenReturn(false);

        //WHEN
        boolean existePorPatente = this.vehiculoService.existePorPatente(patente);

        //THEN
        assertFalse(existePorPatente);
        verify(this.vehiculoRepository).existsByPatente(anyString());
    }

    @Test
    void buscarPorPatente_RetornaOptionalConVehiculo() {
        //GIVEN
        String patente = "ABC123";
        Optional<Vehiculo> esperado = Optional.of(VehiculoTestUtil.generarVehiculo());

        when(this.vehiculoRepository.findByPatente(anyString()))
                .thenReturn(esperado);

        //WHEN
        Optional<Vehiculo> actual = this.vehiculoService.buscarPorPatente(patente);

        //THEN
        assertTrue(actual.isPresent());
        assertEquals(esperado, actual);
        verify(this.vehiculoRepository).findByPatente(anyString());
    }

    @Test
    void buscarPorPatente_RetornaOptionalVacio() {
        //GIVEN
        String patente = "ABC123";
        Optional<Vehiculo> esperado = Optional.empty();

        when(this.vehiculoRepository.findByPatente(anyString()))
                .thenReturn(esperado);

        //WHEN
        Optional<Vehiculo> actual = this.vehiculoService.buscarPorPatente(patente);

        //THEN
        assertTrue(actual.isEmpty());
        assertEquals(esperado, actual);
        verify(this.vehiculoRepository).findByPatente(anyString());
    }

    @Test
    void crear_RetornaVehiculo() {
        //GIVEN
        Vehiculo vehiculo = VehiculoTestUtil.generarVehiculoSinId();
        Vehiculo esperado = VehiculoTestUtil.generarVehiculo();

        when(this.vehiculoRepository.save(any(Vehiculo.class))).thenReturn(esperado);

        //WHEN
        Vehiculo actual = this.vehiculoService.crear(vehiculo);

        //THEN
        assertNotNull(actual.getId());
        assertEquals(esperado, actual);
        verify(this.vehiculoRepository).save(any(Vehiculo.class));
    }

    @Test
    void crear_RetornaExcepcion_CuandoVehiculoEsNulo() {
        //GIVEN
        Vehiculo vehiculo = null;

        //WHEN Y THEN
        assertThrows(VehiculoException.class, ()-> {
            this.vehiculoService.crear(vehiculo);
        });
        verify(this.vehiculoRepository, never()).save(any(Vehiculo.class));
    }

}