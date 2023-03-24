package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.dto.request.RepuestoInDto;
import com.besysoft.bootcamp.dto.response.RepuestoOutDto;

public class RepuestoTestUtil {

    public static RepuestoInDto generarRepuestoInDto(){
        RepuestoInDto dto = new RepuestoInDto();
        dto.setMarca("Fell");
        dto.setModelo("Zarcon");
        dto.setNombre("Amortiguador");
        dto.setValor("1234.56");

        return dto;

    }

    public static RepuestoOutDto generarRepuestoOutDto(){

        RepuestoOutDto dto = new RepuestoOutDto();
        dto.setMarca("Fell");
        dto.setModelo("Zarcon");
        dto.setNombre("Amortiguador");
        dto.setValor("1234.56");

        return dto;

    }

}
