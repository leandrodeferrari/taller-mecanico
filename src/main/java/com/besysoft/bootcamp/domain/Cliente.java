package com.besysoft.bootcamp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Table(name = "clientes")
@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "APELLIDO", length = 80, nullable = false)
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

    @Column(name = "CORREO_ELECTRONICO", length = 255, unique = true)
    private String correoElectronico;

    @Column(name = "NOMBRES", length = 100, nullable = false)
    private String nombres;

    @Column(name = "TELEFONO_LINEA", length = 15)
    private String telefonoDeLinea;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "clientes")
    private List<Vehiculo> vehiculos;

}
