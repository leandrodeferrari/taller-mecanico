package com.besysoft.bootcamp.dto.mapper.impl;

import com.besysoft.bootcamp.domain.entity.OrdenDeTrabajo;
import com.besysoft.bootcamp.dto.mapper.IFacturacionMapper;

import com.besysoft.bootcamp.dto.response.FacturacionOutDto;
import com.besysoft.bootcamp.exception.OrdenDeTrabajoException;
import org.springframework.stereotype.Component;

@Component
public class FacturacionMapperImpl implements IFacturacionMapper {

    @Override
    public FacturacionOutDto mapToDto(OrdenDeTrabajo ordenDeTrabajo) {
        if(ordenDeTrabajo == null){
            throw new OrdenDeTrabajoException("Orden de trabajo no puede ser nulo");
        }

        FacturacionOutDto dto = new FacturacionOutDto();
        dto.setOrdenDeTrabajoId(ordenDeTrabajo.getId());
        dto.setEstado(ordenDeTrabajo.getEstado());
        dto.setFormaDePago(ordenDeTrabajo.getFormaDePago());
        dto.setCantidadDeCuotas(ordenDeTrabajo.getCantidadDeCuotas());
        dto.setImporteTotal(ordenDeTrabajo.getImporteTotal().toPlainString());
        dto.setAdministrativoId(ordenDeTrabajo.getAdministrativo().getId());
        dto.setFechaDelPago(ordenDeTrabajo.getFechaDelPago().toString());
        dto.setTipoDeTarjeta(ordenDeTrabajo.getTipoDeTarjeta());

        return dto;

    }

}
