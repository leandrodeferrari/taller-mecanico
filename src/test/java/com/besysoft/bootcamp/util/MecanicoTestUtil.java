package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.entity.Mecanico;
import com.besysoft.bootcamp.domain.enums.ActivoEnum;
import com.besysoft.bootcamp.dto.response.MecanicoOutDto;

public class MecanicoTestUtil {

    public static Mecanico generarMecanico(){

        Mecanico mecanico = new Mecanico();
        mecanico.setId(1L);
        mecanico.setActivo(ActivoEnum.VERDADERO.valor);
        mecanico.setApellido("Gomez");
        mecanico.setNombres("Leandro Gonzalo");
        mecanico.setEspecialidad("Reparación");

        return mecanico;

    }

    public static Mecanico generarMecanicoInactivo(){

        Mecanico mecanico = new Mecanico();
        mecanico.setId(1L);
        mecanico.setActivo(ActivoEnum.FALSO.valor);
        mecanico.setApellido("Gomez");
        mecanico.setNombres("Leandro Gonzalo");
        mecanico.setEspecialidad("Reparación");

        return mecanico;

    }

    public static MecanicoOutDto generarMecanicoOutDto(){

        MecanicoOutDto dto = new MecanicoOutDto();
        dto.setId(1L);
        dto.setActivo(ActivoEnum.VERDADERO.valor);
        dto.setApellido("Gomez");
        dto.setNombres("Leandro Gonzalo");
        dto.setEspecialidad("Reparación");

        return dto;

    }

}
