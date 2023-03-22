package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.entity.ManoDeObra;
import com.besysoft.bootcamp.dto.response.ManoDeObraOutDto;

import java.time.LocalTime;

public class ManoDeObraTestUtil {

    public static ManoDeObra generarManoDeObraSinId(){

        ManoDeObra manoDeObra = new ManoDeObra();
        manoDeObra.setDetalle("Arreglo de los amortiguadores traseros");
        manoDeObra.setDuracionEnHoras(LocalTime.now());

        return manoDeObra;

    }

    public static ManoDeObra generarManoDeObraConId(){

        ManoDeObra manoDeObra = new ManoDeObra();
        manoDeObra.setId(1L);
        manoDeObra.setDetalle("Arreglo de los amortiguadores traseros");
        manoDeObra.setDuracionEnHoras(LocalTime.now());

        return manoDeObra;

    }

    public static ManoDeObraOutDto generarManoDeObraOutDto(){

        ManoDeObraOutDto dto = new ManoDeObraOutDto();
        dto.setId(1L);
        dto.setMecanico(MecanicoTestUtil.generarMecanicoOutDto());
        dto.setOrdenDeTrabajo(OrdenDeTrabajoTestUtil.generarOrdenDeTrabajoOutDto());

        return dto;

    }

}
