package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.exception.MecanicoException;

public class MecanicoUtil {

    public static void validarActivo(Character activo){

        String activoCharacter = activo.toString();

        if(!activoCharacter.matches("^[VF]$")){
            throw new MecanicoException("Activo solamente puede ser V (verdadero) o F (falso)");
        }

    }

}
