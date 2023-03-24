package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.dto.request.ClienteVehiculoInDto;
import com.besysoft.bootcamp.dto.response.ClienteVehiculoOutDto;
import com.besysoft.bootcamp.service.IClienteService;
import com.besysoft.bootcamp.util.ClienteTestUtil;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IClienteService clienteService;

    private ObjectMapper objectMapper;
    private String url;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
        this.url = "/clientes";
    }

    @Test
    void recibirClienteVehiculo() throws Exception {
        //GIVEN
        ClienteVehiculoInDto dto = ClienteTestUtil.generarClienteVehiculoInDto();
        ClienteVehiculoOutDto esperado = ClienteTestUtil.generarClienteVehiculoOutDto();

        when(this.clienteService.recibirClienteVehiculo(any(ClienteVehiculoInDto.class))).thenReturn(esperado);

        //WHEN
        this.mvc.perform(post(this.url.concat("/clientes-vehiculos"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(dto)))
                //THEN
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(esperado)));
        verify(this.clienteService).recibirClienteVehiculo(any(ClienteVehiculoInDto.class));
    }

}