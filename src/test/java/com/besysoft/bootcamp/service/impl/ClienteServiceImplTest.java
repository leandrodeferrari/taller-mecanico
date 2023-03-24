package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.Cliente;
import com.besysoft.bootcamp.domain.entity.Vehiculo;
import com.besysoft.bootcamp.dto.mapper.IClienteVehiculoMapper;
import com.besysoft.bootcamp.dto.request.ClienteVehiculoInDto;
import com.besysoft.bootcamp.dto.response.ClienteVehiculoOutDto;
import com.besysoft.bootcamp.repository.IClienteRepository;
import com.besysoft.bootcamp.service.IVehiculoService;

import com.besysoft.bootcamp.util.ClienteTestUtil;
import com.besysoft.bootcamp.util.VehiculoTestUtil;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

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

    private static final String INFO_SE_CREAN_AMBOS = "Se creó Cliente y Vehículo";
    private static final String INFO_SE_CREO_VEHICULO = "Ya existía cliente y se creó Vehículo";
    private static final String INFO_SE_CREO_CLIENTE = "Ya existía Vehiculo y se creó Cliente";
    private static final String INFO_YA_EXISTIAN = "Ya existían Cliente y Vehiculo";

    @Test
    void recibirClienteVehiculo() {
        //GIVEN
        ClienteVehiculoInDto inDto = ClienteTestUtil.generarClienteVehiculoInDto();
        Optional<Vehiculo> optionalVehiculo = Optional.of(VehiculoTestUtil.generarVehiculo());
        Optional<Cliente> optionalCliente = Optional.of(ClienteTestUtil.generarCliente());

        ClienteVehiculoOutDto esperado = this.clienteVehiculoMapper.mapToDto(optionalCliente.get(),
                optionalVehiculo.get(), INFO_YA_EXISTIAN);

        when(this.vehiculoService.buscarPorPatente(anyString())).thenReturn(optionalVehiculo);
        when(this.clienteRepository.findByCorreoElectronico(anyString())).thenReturn(optionalCliente);

        //WHEN
        ClienteVehiculoOutDto actual = this.clienteService.recibirClienteVehiculo(inDto);

        //THEN
        assertEquals(esperado, actual);
        verify(this.vehiculoService).buscarPorPatente(anyString());
        verify(this.clienteRepository).findByCorreoElectronico(anyString());
        verify(this.clienteRepository, never()).save(any(Cliente.class));
        verify(this.vehiculoService, never()).crear(any(Vehiculo.class));
    }

}