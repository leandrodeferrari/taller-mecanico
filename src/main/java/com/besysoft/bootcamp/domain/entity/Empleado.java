package com.besysoft.bootcamp.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "empleados")
@Entity
public class Empleado extends Persona {

    @Column(name = "APELLIDO", length = 255)
    private String apellido;

    @Column(name = "NOMBRES", length = 255)
    private String nombres;

    @Column(name = "TIPO_EMPLEADO", length = 255)
    private String tipoDeEmpleado;

}
