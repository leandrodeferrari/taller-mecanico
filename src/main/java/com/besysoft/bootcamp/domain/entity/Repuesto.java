package com.besysoft.bootcamp.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Table(name = "repuestos")
@Entity
public class Repuesto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MARCA", length = 255)
    private String marca;

    @Column(name = "MODELO", length = 255)
    private String modelo;

    @Column(name = "REPUESTO", length = 255)
    private String repuesto;

    @Column(name = "VALOR", precision = 19, scale = 2)
    private BigDecimal valor;

}
