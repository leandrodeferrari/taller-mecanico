package com.besysoft.bootcamp.dto.response;

import lombok.Data;

@Data
public class ManoDeObraOutDto {

    private Long id;
    private String detalle;
    private String duracionEnHoras;
    private MecanicoOutDto mecanico;
    private OrdenDeTrabajoOutDto ordenDeTrabajo;

}
