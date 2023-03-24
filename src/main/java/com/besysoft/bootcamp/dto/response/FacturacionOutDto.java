package com.besysoft.bootcamp.dto.response;

import lombok.Data;

@Data
public class FacturacionOutDto {

    private Long ordenDeTrabajoId;
    private Integer cantidadDeCuotas;
    private String estado;
    private String fechaDelPago;
    private String formaDePago;
    private String importeTotal;
    private String tipoDeTarjeta;
    private Long administrativoId;

}
