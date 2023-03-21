package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.dto.request.ClienteVehiculoInDto;
import com.besysoft.bootcamp.dto.response.ClienteVehiculoOutDto;
import com.besysoft.bootcamp.service.IClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "Endpoints - Cliente", tags = "Acciones permitidas para la entidad: Cliente")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final IClienteService clienteService;

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/clientes-vehiculos")
    @ApiOperation(value = "Permite recibir al Cliente y Vehículo - Actividad del caso número 1")
    public ResponseEntity<ClienteVehiculoOutDto> recibirClienteVehiculo(@Valid @RequestBody ClienteVehiculoInDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clienteService.recibirClienteVehiculo(dto));
    }

}
