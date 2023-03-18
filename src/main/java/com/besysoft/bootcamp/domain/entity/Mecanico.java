package com.besysoft.bootcamp.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Set;

@Getter
@Setter
@Table(name = "mecanicos")
@Entity
public class Mecanico extends Persona {

    @Column(name = "ACTIVO", length = 1)
    private Character activo;

    @Column(name = "APELLIDO", length = 255)
    private String apellido;

    @Column(name = "ESPECIALIDAD", length = 255)
    private String especialidad;

    @Column(name = "NOMBRES", length = 255)
    private String nombres;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mecanico")
    private Set<ManoDeObra> manosDeObra;

}
