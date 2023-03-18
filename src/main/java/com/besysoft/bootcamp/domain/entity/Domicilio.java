package com.besysoft.bootcamp.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class Domicilio implements Serializable {

    @Column(name = "CALLE", length = 255)
    private String calle;

    @Column(name = "CODIGO_POSTAL", length = 255)
    private String codigoPostal;

    @Column(name = "DEPARTAMENTO", length = 255)
    private String departamento;

    @Column(name = "LOCALIDAD", length = 255)
    private String localidad;

    @Column(name = "NUMERO", length = 255)
    private String numero;

    @Column(name = "PISO", length = 255)
    private String piso;

}
