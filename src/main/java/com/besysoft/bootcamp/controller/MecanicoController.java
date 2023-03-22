package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.dto.response.ManoDeObraOutDto;
import com.besysoft.bootcamp.service.IMecanicoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
