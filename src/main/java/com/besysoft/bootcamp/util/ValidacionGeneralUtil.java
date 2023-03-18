package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.exception.IdException;

public class ValidacionGeneralUtil {

    public static void validarId(Long id){

        if(id == null){
            throw new IdException("El ID no puede ser nulo");
        }

        if(id < 1){
            throw new IdException("El ID no puede ser menor a 1");
        }

    }

}
