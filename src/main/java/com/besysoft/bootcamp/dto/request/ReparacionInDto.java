package com.besysoft.bootcamp.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ReparacionInDto {

    // Datos de Mano de Obra
    private String detalle;
    private String duracionEnHoras;

    //Datos de Repuestos
    private List<Long> repuestosId;

}
