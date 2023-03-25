package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.domain.enums.EstadoEnum;
import com.besysoft.bootcamp.dto.request.FacturacionInDto;
import com.besysoft.bootcamp.dto.request.OrdenDeTrabajoInDto;
import com.besysoft.bootcamp.dto.response.FacturacionOutDto;
import com.besysoft.bootcamp.dto.response.OrdenDeTrabajoOutDto;
import com.besysoft.bootcamp.service.IOrdenDeTrabajoService;
import com.besysoft.bootcamp.util.FacturacionTestUtil;
import com.besysoft.bootcamp.util.OrdenDeTrabajoTestUtil;

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

@WebMvcTest(OrdenDeTrabajoController.class)
class OrdenDeTrabajoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IOrdenDeTrabajoService ordenDeTrabajoService;

    private ObjectMapper objectMapper;
    private String url;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
        this.url = "/ordenes-de-trabajo";
    }

    @Test
    void generar() throws Exception {
        //GIVEN
        OrdenDeTrabajoInDto dto = OrdenDeTrabajoTestUtil.generarOrdenDeTrabajoInDto();
        OrdenDeTrabajoOutDto esperado = OrdenDeTrabajoTestUtil.generarOrdenDeTrabajoOutDto();

        when(this.ordenDeTrabajoService.crear(any(OrdenDeTrabajoInDto.class))).thenReturn(esperado);

        //WHEN
        this.mvc.perform(post(this.url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(dto)))
                //THEN
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(esperado)));
        verify(this.ordenDeTrabajoService).crear(any(OrdenDeTrabajoInDto.class));
    }

    @Test
    void entregarVehiculo() throws Exception {
        //GIVEN
        Long id = 1L;
        OrdenDeTrabajoOutDto esperado = OrdenDeTrabajoTestUtil.generarOrdenDeTrabajoCerradaDto();

        when(this.ordenDeTrabajoService.entregarVehiculo(anyLong())).thenReturn(esperado);

        //WHEN
        this.mvc.perform(patch(this.url.concat("/{id}"), id)
                        .contentType(MediaType.APPLICATION_JSON))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(esperado)))
                .andExpect(jsonPath("$.estado").value(EstadoEnum.CERRADA.valor));
        verify(this.ordenDeTrabajoService).entregarVehiculo(anyLong());
    }

    @Test
    void facturar() throws Exception {
        //GIVEN
        Long administrativoId = 1L;
        Long ordenDeTrabajoId = 1L;
        FacturacionInDto dto = FacturacionTestUtil.generarFacturacionInDto();
        FacturacionOutDto esperado = FacturacionTestUtil.generarFacturacionOutDto();

        when(this.ordenDeTrabajoService.facturar(any(FacturacionInDto.class), anyLong(), anyLong()))
                .thenReturn(esperado);

        //WHEN
        this.mvc.perform(patch(this.url
                .concat("/{ordenDeTrabajoId}/administrativos/{administrativoId}"),
                ordenDeTrabajoId, administrativoId).contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(dto)))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(esperado)));
        verify(this.ordenDeTrabajoService).facturar(any(FacturacionInDto.class), anyLong(), anyLong());
    }

}