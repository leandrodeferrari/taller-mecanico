package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.dto.request.ReparacionInDto;
import com.besysoft.bootcamp.dto.response.ManoDeObraOutDto;
import com.besysoft.bootcamp.dto.response.ReparacionOutDto;
import com.besysoft.bootcamp.dto.response.RepuestoOutDto;

import java.util.List;

public class ReparacionTestUtil {

    public static ReparacionInDto generarReparacionInDto(){

        ReparacionInDto dto = new ReparacionInDto();
        dto.setDetalle("Arreglos varios");
        dto.setDuracionEnHoras("09:00:00");
        List<Long> ids = List.of(1L);
        dto.setRepuestosId(ids);

        return dto;

    }

    public static ReparacionOutDto generarReparacionOutDto(){

        ReparacionOutDto dto = new ReparacionOutDto();
        RepuestoOutDto repuestoDto = RepuestoTestUtil.generarRepuestoOutDto();
        ManoDeObraOutDto manoDeObraDto = ManoDeObraTestUtil.generarManoDeObraOutDto();
        dto.setRepuestos(List.of(repuestoDto));
        dto.setManoDeObra(manoDeObraDto);

        return dto;

    }


}
