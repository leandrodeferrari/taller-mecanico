package com.besysoft.bootcamp.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;

@Getter
@Setter
@Table(name = "mecanicos")
@Entity
public class Mecanico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ACTIVO", length = 1)
    private Character activo;

    @Column(name = "APELLIDO", length = 255)
    private String apellido;

    @Column(name = "CELULAR", length = 15)
    private String celular;

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

    @Column(name = "ESPECIALIDAD", length = 255)
    private String especialidad;

    @Column(name = "NOMBRES", length = 255)
    private String nombres;

}
