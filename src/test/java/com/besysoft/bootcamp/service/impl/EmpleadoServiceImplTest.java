package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.Empleado;
import com.besysoft.bootcamp.repository.IEmpleadoRepository;
import com.besysoft.bootcamp.util.EmpleadoTestUtil;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmpleadoServiceImplTest {

    @MockBean
    private IEmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoServiceImpl empleadoService;

    @Test
    void buscarPorId() {
        //GIVEN
        Long id = 1L;
        Optional<Empleado> esperado = Optional.of(EmpleadoTestUtil.generarRecepcionista());

        when(this.empleadoRepository.findById(anyLong())).thenReturn(esperado);

        //WHEN
        Optional<Empleado> actual = this.empleadoService.buscarPorId(id);

        //THEN
        assertTrue(actual.isPresent());
        assertEquals(esperado, actual);
        verify(this.empleadoRepository).findById(anyLong());
    }

}