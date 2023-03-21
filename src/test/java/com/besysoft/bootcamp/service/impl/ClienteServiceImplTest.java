package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.dto.mapper.IClienteVehiculoMapper;
import com.besysoft.bootcamp.repository.IClienteRepository;
import com.besysoft.bootcamp.service.IVehiculoService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClienteServiceImplTest {

    @MockBean
    private IClienteRepository clienteRepository;

    @MockBean
    private IVehiculoService vehiculoService;

    @Autowired
    private IClienteVehiculoMapper clienteVehiculoMapper;

    @Autowired
    private ClienteServiceImpl clienteService;

    @Test
    void recibirClienteVehiculo() {
        //GIVEN


        //WHEN


        //THEN

    }

    @Test
    void existePorCorreoElectronico_RetornaVerdadero() {
        //GIVEN
        String correoElectronico = "juan_gomez@hotmail.com";

        when(this.clienteRepository.existsByCorreoElectronico(anyString())).thenReturn(true);

        //WHEN
        boolean existePorCorreoElectronico = this.clienteService.existePorCorreoElectronico(correoElectronico);

        //THEN
        assertTrue(existePorCorreoElectronico);
        verify(this.clienteRepository).existsByCorreoElectronico(anyString());
    }

    @Test
    void existePorCorreoElectronico_RetornaFalso() {
        //GIVEN
        String correoElectronico = "juan_gomez@hotmail.com";

        when(this.clienteRepository.existsByCorreoElectronico(anyString())).thenReturn(false);

        //WHEN
        boolean existePorCorreoElectronico = this.clienteService.existePorCorreoElectronico(correoElectronico);

        //THEN
        assertFalse(existePorCorreoElectronico);
        verify(this.clienteRepository).existsByCorreoElectronico(anyString());
    }

}