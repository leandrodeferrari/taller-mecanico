package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.entity.OrdenDeTrabajo;
import com.besysoft.bootcamp.domain.enums.EstadoEnum;
import com.besysoft.bootcamp.dto.request.OrdenDeTrabajoInDto;
import com.besysoft.bootcamp.dto.response.OrdenDeTrabajoOutDto;

import java.time.LocalDateTime;

public class OrdenDeTrabajoTestUtil {

    public static OrdenDeTrabajoInDto generarOrdenDeTrabajoInDto(){

        OrdenDeTrabajoInDto dto = new OrdenDeTrabajoInDto();
        dto.setKilometraje(120_000L);
        dto.setDetalleDeLaFalla("Falla en el cilindro");
        dto.setNivelDelCombustible("Lleno");
        dto.setRecepcionistaId(EmpleadoTestUtil.generarRecepcionista().getId());

        return dto;

    }

    public static OrdenDeTrabajoOutDto generarOrdenDeTrabajoOutDto(){

        OrdenDeTrabajoOutDto dto = new OrdenDeTrabajoOutDto();
        dto.setKilometraje(120_000L);
        dto.setDetalleDeLaFalla("Falla en el cilindro");
        dto.setNivelDelCombustible("Lleno");
        dto.setId(1L);
        dto.setEstado(EstadoEnum.CREADA.valor);
        dto.setFechaDeIngreso(LocalDateTime.now().toString());
        dto.setRecepcionistaId(EmpleadoTestUtil.generarRecepcionista().getId());
        dto.setNombreRecepcionista(EmpleadoTestUtil.generarRecepcionista().getNombres());

        return dto;

    }

    public static OrdenDeTrabajo generarOrdenDeTrabajoFacturada(){

        OrdenDeTrabajo ordenDeTrabajo = new OrdenDeTrabajo();
        ordenDeTrabajo.setId(1L);
        ordenDeTrabajo.setEstado(EstadoEnum.FACTURADA.valor);

        return ordenDeTrabajo;

    }

    public static OrdenDeTrabajo generarOrdenDeTrabajoCreada(){

        OrdenDeTrabajo ordenDeTrabajo = new OrdenDeTrabajo();
        ordenDeTrabajo.setId(1L);
        ordenDeTrabajo.setEstado(EstadoEnum.CREADA.valor);

        return ordenDeTrabajo;

    }

    public static OrdenDeTrabajo generarOrdenDeTrabajoEnReparacion(){

        OrdenDeTrabajo ordenDeTrabajo = new OrdenDeTrabajo();
        ordenDeTrabajo.setId(1L);
        ordenDeTrabajo.setEstado(EstadoEnum.EN_REPARACION.valor);

        return ordenDeTrabajo;

    }

    public static OrdenDeTrabajo generarOrdenDeTrabajoCerrada(){

        OrdenDeTrabajo ordenDeTrabajo = new OrdenDeTrabajo();
        ordenDeTrabajo.setId(1L);
        ordenDeTrabajo.setEstado(EstadoEnum.CERRADA.valor);

        return ordenDeTrabajo;

    }

    public static OrdenDeTrabajoOutDto generarOrdenDeTrabajoCerradaDto(){

        OrdenDeTrabajo ordenDeTrabajo = generarOrdenDeTrabajoCerrada();

        OrdenDeTrabajoOutDto dto = new OrdenDeTrabajoOutDto();
        dto.setId(ordenDeTrabajo.getId());
        dto.setEstado(ordenDeTrabajo.getEstado());

        return dto;

    }

}
