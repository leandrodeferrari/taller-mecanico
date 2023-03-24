package com.besysoft.bootcamp.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class FacturacionInDto {

    private List<DetalleInDto> detalles;
    private String formaDePago;
    private String tipoDeTarjeta;
    private Integer cantidadDeCuotas;

}
