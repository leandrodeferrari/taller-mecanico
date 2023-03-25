package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.entity.DetalleOrdenDeTrabajo;
import com.besysoft.bootcamp.dto.request.DetalleInDto;

import java.util.Arrays;
import java.util.List;

public class DetalleOrdenDeTrabajoTestUtil {

    public static DetalleOrdenDeTrabajo generarDetalle(){

        DetalleOrdenDeTrabajo detalle = new DetalleOrdenDeTrabajo();
        detalle.setId(1L);
        detalle.setRepuesto(RepuestoTestUtil.generarRepuesto());
        detalle.setOrdenDeTrabajo(OrdenDeTrabajoTestUtil.generarOrdenDeTrabajoEnReparacion());

        return detalle;

    }

    public static DetalleOrdenDeTrabajo generarDetalleSinId(){

        DetalleOrdenDeTrabajo detalle = new DetalleOrdenDeTrabajo();
        detalle.setRepuesto(RepuestoTestUtil.generarRepuesto());
        detalle.setOrdenDeTrabajo(OrdenDeTrabajoTestUtil.generarOrdenDeTrabajoEnReparacion());

        return detalle;

    }

    public static List<DetalleInDto> generarDetallesInDto(){

        DetalleInDto dto1 = new DetalleInDto();
        dto1.setCantidad(2);
        dto1.setDeatalleId(1L);
        DetalleInDto dto2 = new DetalleInDto();
        dto2.setCantidad(1);
        dto2.setDeatalleId(2L);

        return Arrays.asList(dto1, dto2);

    }

}
