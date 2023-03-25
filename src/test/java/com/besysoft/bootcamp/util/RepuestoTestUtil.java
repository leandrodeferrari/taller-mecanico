package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.entity.Repuesto;
import com.besysoft.bootcamp.dto.request.RepuestoInDto;
import com.besysoft.bootcamp.dto.response.RepuestoOutDto;

import java.math.BigDecimal;
import java.util.List;

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

    public static Repuesto generarRepuesto(){

        Repuesto repuesto = new Repuesto();
        repuesto.setId(1L);
        repuesto.setMarca("Fell");
        repuesto.setModelo("Zarcon");
        repuesto.setRepuesto("Amortiguador");
        repuesto.setValor(new BigDecimal("1234.23"));

        return repuesto;

    }

    public static Repuesto generarRepuestoSinId(){

        Repuesto repuesto = new Repuesto();
        repuesto.setMarca("Fell");
        repuesto.setModelo("Zarcon");
        repuesto.setRepuesto("Amortiguador");
        repuesto.setValor(new BigDecimal("1234.23"));

        return repuesto;

    }

    public static List<Repuesto> generarRepuestos(){
        return List.of(generarRepuesto());
    }

}
