package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.dto.request.RepuestoInDto;
import com.besysoft.bootcamp.dto.response.RepuestoOutDto;
import com.besysoft.bootcamp.service.IRepuestoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "Endpoints - Repuesto", tags = "Acciones permitidas para la entidad: Repuesto")
@RestController
@RequestMapping("/repuestos")
public class RepuestoController {

    private final IRepuestoService repuestoService;

    public RepuestoController(IRepuestoService repuestoService) {
        this.repuestoService = repuestoService;
    }

    @PostMapping
    @ApiOperation(value = "Permite crear un Repuesto - Complemento de la Actividad del caso número 4")
    public ResponseEntity<RepuestoOutDto> crear(@Valid @RequestBody RepuestoInDto dto){
        return ResponseEntity.ok(this.repuestoService.crear(dto));
    }

}
