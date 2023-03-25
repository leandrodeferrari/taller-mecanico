package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.enums.EstadoEnum;
import com.besysoft.bootcamp.domain.enums.FormaDePagoEnum;
import com.besysoft.bootcamp.domain.enums.TipoDeTarjetaEnum;
import com.besysoft.bootcamp.dto.request.FacturacionInDto;
import com.besysoft.bootcamp.dto.response.FacturacionOutDto;

public class FacturacionTestUtil {

    public static FacturacionInDto generarFacturacionInDto(){

        FacturacionInDto dto = new FacturacionInDto();
        dto.setCantidadDeCuotas(3);
        dto.setFormaDePago(FormaDePagoEnum.MERCADO_PAGO.valor);
        dto.setTipoDeTarjeta(TipoDeTarjetaEnum.MERCADO_PAGO.valor);
        dto.setDetalles(DetalleOrdenDeTrabajoTestUtil.generarDetallesInDto());

        return dto;

    }

    public static FacturacionOutDto generarFacturacionOutDto(){

        FacturacionOutDto dto = new FacturacionOutDto();
        dto.setAdministrativoId(1L);
        dto.setFechaDelPago("2020-10-10");
        dto.setEstado(EstadoEnum.FACTURADA.valor);
        dto.setImporteTotal("2345.67");
        dto.setCantidadDeCuotas(3);
        dto.setFormaDePago(FormaDePagoEnum.MERCADO_PAGO.valor);
        dto.setTipoDeTarjeta(TipoDeTarjetaEnum.MERCADO_PAGO.valor);

        return dto;

    }

}
