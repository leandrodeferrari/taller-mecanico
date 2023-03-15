package com.besysoft.bootcamp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Table(name = "detalle_ordenes_trabajo")
@Entity
public class DetalleOrdenDeTrabajo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CANTIDAD")
    private Integer cantidad;

    @Column(name = "VALOR_TOTAL", precision = 19, scale = 2)
    private BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn(name = "ORDEN_TRABAJO_ID")
    private OrdenDeTrabajo ordenDeTrabajo;

    @ManyToOne
    @JoinColumn(name = "REPUESTO_ID")
    private Repuesto repuesto;

}
