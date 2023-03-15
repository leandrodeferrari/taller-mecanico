package com.besysoft.bootcamp.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoDeTarjetaEnum {

    MERCADO_PAGO("Mercado pago"),
    CREDITO("Crédito"),
    DEBITO("Débito"),
    CUENTA_DNI("Cuenta DNI");

    private final String nombre;

}
