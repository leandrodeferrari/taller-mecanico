package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.dto.request.MecanicoInDto;
import com.besysoft.bootcamp.dto.response.ManoDeObraOutDto;
import com.besysoft.bootcamp.dto.response.MecanicoOutDto;
import com.besysoft.bootcamp.service.IMecanicoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Endpoints - Mecanico", tags = "Acciones permitidas para la entidad: Mecanico")
@RestController
@RequestMapping("/mecanicos")
public class MecanicoController {

    private final IMecanicoService mecanicoService;

    public MecanicoController(IMecanicoService mecanicoService) {
        this.mecanicoService = mecanicoService;
    }

    @PatchMapping("/{mecanicoId}/ordenes-de-trabajo/{ordenDeTrabajoId}/manos-de-obra")
    @ApiOperation(value = "Permite generar una Mano de Obra - Actividad del caso número 3")
    public ResponseEntity<ManoDeObraOutDto> generarManoDeObra(@PathVariable Long mecanicoId,
                                                              @PathVariable Long ordenDeTrabajoId){
        return ResponseEntity.ok(this.mecanicoService.generarManoDeObra(mecanicoId, ordenDeTrabajoId));
    }

    @PostMapping
    @ApiOperation(value = "Permite crear un Mecanico - Complemento de la Actividad del caso número 3")
    public ResponseEntity<MecanicoOutDto> crear(@Valid @RequestBody MecanicoInDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.mecanicoService.crear(dto));
    }

}
