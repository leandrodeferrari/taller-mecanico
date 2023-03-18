package com.besysoft.bootcamp.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Table(name = "vehiculos")
@Entity
public class Vehiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ANIO")
    private Integer anio;

    @Column(name = "COLOR", length = 255)
    private String color;

    @Column(name = "MARCA", length = 255)
    private String marca;

    @Column(name = "MODELO", length = 255)
    private String modelo;

    @Column(name = "PATENTE", unique = true)
    private String patente;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "cliente_vehiculo",
            joinColumns = @JoinColumn(name = "VEHICULO_ID"),
            inverseJoinColumns = @JoinColumn(name = "CLIENTE_ID")
    )
    private List<Cliente> clientes;

}
