package com.besysoft.bootcamp.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FormaDePagoEnum {

    EFECTIVO("Efectivo"),
    DEBITO("Débito"),
    CREDITO("Crédito"),
    TRANSFERENCIA("Transferencia"),
    MERCADO_PAGO ("Mercado Pago");

    public final String valor;

}