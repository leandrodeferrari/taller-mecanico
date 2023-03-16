package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.dto.request.OrdenDeTrabajoInDto;

public class OrdenDeTrabajoTestUtil {

    public static OrdenDeTrabajoInDto generarOrdenDeTrabajoInDto(){

        OrdenDeTrabajoInDto dto = new OrdenDeTrabajoInDto();
        dto.setKilometraje(120_000L);
        dto.setDetalleDeLaFalla("Falla en el cilindro");
        dto.setNivelDelCombustible("Lleno");

        return dto;

    }

}
