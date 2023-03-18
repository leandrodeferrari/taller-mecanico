package com.besysoft.bootcamp.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CELULAR", length = 15)
    private String celular;

    @Embedded
    private Domicilio domicilio;

}
