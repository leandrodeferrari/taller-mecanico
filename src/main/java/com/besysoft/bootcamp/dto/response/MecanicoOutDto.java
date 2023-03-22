package com.besysoft.bootcamp.dto.response;

import lombok.Data;

@Data
public class MecanicoOutDto {

    private Long id;
    private Character activo;
    private String apellido;
    private String nombres;
    private String especialidad;

}
