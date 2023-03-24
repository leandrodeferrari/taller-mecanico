package com.besysoft.bootcamp.dto.response;

import lombok.Data;

@Data
public class OrdenDeTrabajoOutDto {

    private Long id;
    private String nivelDelCombustible;
    private Long kilometraje;
    private String detalleDeLaFalla;
    private String fechaDeIngreso;
    private String estado;
    private Long recepcionistaId;
    private String nombreRecepcionista;

}
