package com.besysoft.bootcamp.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "ordenes_trabajo")
@Entity
public class OrdenDeTrabajo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CANTIDAD_CUOTAS")
    private Integer cantidadDeCuotas;

    @Column(name = "DETALLE_FALLA", length = 255)
    private String detalleDeLaFalla;

    @Column(name = "ESTADO", length = 255)
    private String estado;

    @Column(name = "FECHA_FIN_REPARACION")
    private LocalDateTime fechaFinDeReparacion;

    @Column(name = "FECHA_INGRESO")
    private LocalDateTime fechaDeIngreso;

    @Column(name = "FECHA_PAGO")
    private LocalDateTime fechaDelPago;

    @Column(name = "FORMA_PAGO", length = 255)
    private String formaDePago;

    @Column(name = "IMPORTE_TOTAL", precision = 19, scale = 2)
    private BigDecimal importeTotal;

    @Column(name = "KILOMETRAJE")
    private Long kilometraje;

    @Column(name = "NIVEL_COMBUSTIBLE", length = 255)
    private String nivelDelCombustible;

    @Column(name = "TIPO_TARJETA", length = 255)
    private String tipoDeTarjeta;

    @ManyToOne
    @JoinColumn(name = "ADMINISTRATIVO_ID")
    private Empleado administrativo;

    @ManyToOne
    @JoinColumn(name = "RECEPCIONISTA_ID")
    private Empleado recepcionista;

    @ManyToOne
    @JoinColumn(name = "VEHICULO_ID")
    private Vehiculo vehiculo;

}
