package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.dto.request.MecanicoInDto;
import com.besysoft.bootcamp.dto.request.ReparacionInDto;
import com.besysoft.bootcamp.dto.response.ManoDeObraOutDto;
import com.besysoft.bootcamp.dto.response.MecanicoOutDto;
import com.besysoft.bootcamp.dto.response.OrdenDeTrabajoOutDto;
import com.besysoft.bootcamp.dto.response.ReparacionOutDto;
import com.besysoft.bootcamp.service.IMecanicoService;
import com.besysoft.bootcamp.util.ManoDeObraTestUtil;
import com.besysoft.bootcamp.util.MecanicoTestUtil;
import com.besysoft.bootcamp.util.OrdenDeTrabajoTestUtil;
import com.besysoft.bootcamp.util.ReparacionTestUtil;

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

@WebMvcTest(MecanicoController.class)
class MecanicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IMecanicoService mecanicoService;

    private ObjectMapper objectMapper;
    private String url;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
        this.url = "/mecanicos";
    }

    @Test
    void generarManoDeObra() throws Exception {
        //GIVEN
        Long mecanicoId = 1L;
        Long ordenDeTrabajoId = 1L;
        ManoDeObraOutDto esperado = ManoDeObraTestUtil.generarManoDeObraOutDto();

        when(this.mecanicoService.generarManoDeObra(anyLong(), anyLong())).thenReturn(esperado);

        //WHEN
        this.mvc.perform(
                patch(this.url.concat("/{mecanicoId}/ordenes-de-trabajo/{ordenDeTrabajoId}/manos-de-obra"),
                mecanicoId, ordenDeTrabajoId)
                        .contentType(MediaType.APPLICATION_JSON))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(esperado)));
        verify(this.mecanicoService).generarManoDeObra(anyLong(), anyLong());
    }

    @Test
    void crear_RetornaMecanicoOutDto() throws Exception {
        //GIVEN
        MecanicoInDto dto = MecanicoTestUtil.generarMecanicoInDto();
        MecanicoOutDto esperado = MecanicoTestUtil.generarMecanicoOutDto();

        when(this.mecanicoService.crear(any(MecanicoInDto.class))).thenReturn(esperado);

        //WHEN
        this.mvc.perform(post(this.url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(dto)))
                //THEN
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(esperado)));
        verify(this.mecanicoService).crear(any(MecanicoInDto.class));
    }

    @Test
    void iniciarReparacion() throws Exception {
        //GIVEN
        Long ordenDeTrabajoId = 1L;
        OrdenDeTrabajoOutDto esperado = OrdenDeTrabajoTestUtil.generarOrdenDeTrabajoOutDto();

        when(this.mecanicoService.iniciarReparacion(anyLong())).thenReturn(esperado);

        //WHEN
        this.mvc.perform(patch(this.url.concat("/ordenes-de-trabajo/{ordenDeTrabajoId}"), ordenDeTrabajoId)
                .contentType(MediaType.APPLICATION_JSON))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(esperado)));
        verify(this.mecanicoService).iniciarReparacion(anyLong());
    }

    @Test
    void finalizarReparacion() throws Exception {
        //GIVEN
        Long manoDeObraId = 1L;
        Long ordenDeTrabajoId = 1L;
        ReparacionInDto dto = ReparacionTestUtil.generarReparacionInDto();
        ReparacionOutDto esperado = ReparacionTestUtil.generarReparacionOutDto();

        when(this.mecanicoService.finalizarReparacion(any(ReparacionInDto.class), anyLong(), anyLong()))
                .thenReturn(esperado);

        //WHEN
        this.mvc.perform(patch(this.url
                .concat("/manos-de-obra/{manoDeObraId}/ordenes-de-trabajo/{ordenDeTrabajoId}/repuestos"),
                manoDeObraId, ordenDeTrabajoId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(dto)))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(esperado)));
        verify(this.mecanicoService).finalizarReparacion(any(ReparacionInDto.class), anyLong(), anyLong());
    }

}