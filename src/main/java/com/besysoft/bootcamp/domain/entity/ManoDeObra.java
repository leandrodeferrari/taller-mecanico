package com.besysoft.bootcamp.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
@Table(name = "mano_obra")
@Entity
public class ManoDeObra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DETALLE", length = 255)
    private String detalle;

    @Column(name = "DURACION_HS")
    private LocalTime duracionEnHoras;

    @ManyToOne
    @JoinColumn(name = "MECANICO_ID")
    private Mecanico mecanico;

    @ManyToOne
    @JoinColumn(name = "ORDEN_TRABAJO_ID")
    private OrdenDeTrabajo ordenDeTrabajo;

}
