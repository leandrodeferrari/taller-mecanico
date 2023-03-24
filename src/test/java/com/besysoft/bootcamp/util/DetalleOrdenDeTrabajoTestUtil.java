package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.entity.DetalleOrdenDeTrabajo;

public class DetalleOrdenDeTrabajoTestUtil {

    public static DetalleOrdenDeTrabajo generarDetalle(){

        DetalleOrdenDeTrabajo detalle = new DetalleOrdenDeTrabajo();
        detalle.setId(1L);
        detalle.setRepuesto(RepuestoTestUtil.generarRepuesto());
        detalle.setOrdenDeTrabajo(OrdenDeTrabajoTestUtil.generarOrdenDeTrabajoEnReparacion());

        return detalle;

    }

}
