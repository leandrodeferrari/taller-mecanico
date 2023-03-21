package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.dto.request.OrdenDeTrabajoInDto;
import com.besysoft.bootcamp.dto.response.OrdenDeTrabajoOutDto;
import com.besysoft.bootcamp.service.IOrdenDeTrabajoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Endpoints - Orden de Trabajo", tags = "Acciones permitidas para la entidad: OrdenDeTrabajo")
@RestController
@RequestMapping("/ordenes-de-trabajo")
public class OrdenDeTrabajoController {

    private final IOrdenDeTrabajoService ordenDeTrabajoService;

    public OrdenDeTrabajoController(IOrdenDeTrabajoService ordenDeTrabajoService) {
        this.ordenDeTrabajoService = ordenDeTrabajoService;
    }

    @PostMapping
    @ApiOperation(value = "Permite generar una Orden de Trabajo - Actividad del caso número 2")
    public ResponseEntity<OrdenDeTrabajoOutDto> generar(@Valid @RequestBody OrdenDeTrabajoInDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.ordenDeTrabajoService.crear(dto));
    }

    @PatchMapping("/{id}")
    @ApiOperation(value = "Permite la entrega del Vehículo - Actividad del caso número 6")
    public ResponseEntity<OrdenDeTrabajoOutDto> entregarVehiculo(@PathVariable Long id){
        return ResponseEntity.ok(this.ordenDeTrabajoService.entregarVehiculo(id));
    }

}
