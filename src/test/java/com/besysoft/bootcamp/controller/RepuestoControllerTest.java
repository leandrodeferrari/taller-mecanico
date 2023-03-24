package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.dto.request.RepuestoInDto;
import com.besysoft.bootcamp.dto.response.RepuestoOutDto;
import com.besysoft.bootcamp.service.IRepuestoService;
import com.besysoft.bootcamp.util.RepuestoTestUtil;

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

@WebMvcTest(RepuestoController.class)
class RepuestoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IRepuestoService repuestoService;

    private ObjectMapper objectMapper;
    private String url;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
        this.url = "/repuestos";
    }

    @Test
    void crear() throws Exception {
        //GIVEN
        RepuestoInDto dto = RepuestoTestUtil.generarRepuestoInDto();
        RepuestoOutDto esperado = RepuestoTestUtil.generarRepuestoOutDto();

        when(this.repuestoService.crear(any(RepuestoInDto.class))).thenReturn(esperado);

        //WHEN
        this.mvc.perform(post(this.url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(dto)))
                //THEN
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(esperado)));
        verify(this.repuestoService).crear(any(RepuestoInDto.class));
    }

}