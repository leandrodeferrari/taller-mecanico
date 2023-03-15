package com.besysoft.bootcamp.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoDeEmpleadoEnum {

    ADMINISTRATIVO("Administrativo"),
    RECEPCIONISTA("Recepcionista");

    private final String nombre;

}
