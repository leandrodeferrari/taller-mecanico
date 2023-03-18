package com.besysoft.bootcamp.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

@Getter
@Setter
@Table(name = "clientes")
@Entity
public class Cliente extends Persona {

    @Column(name = "APELLIDO", length = 80, nullable = false)
    private String apellido;

    @Column(name = "CORREO_ELECTRONICO", length = 255, unique = true)
    private String correoElectronico;

    @Column(name = "NOMBRES", length = 100, nullable = false)
    private String nombres;

    @Column(name = "TELEFONO_LINEA", length = 15)
    private String telefonoDeLinea;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "clientes")
    private List<Vehiculo> vehiculos;

}
