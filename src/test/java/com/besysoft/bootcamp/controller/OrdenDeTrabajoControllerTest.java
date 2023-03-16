package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.service.IOrdenDeTrabajoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

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
    void generar() {
    }

}