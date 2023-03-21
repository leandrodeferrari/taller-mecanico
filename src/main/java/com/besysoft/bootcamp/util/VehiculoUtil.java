package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.exception.VehiculoException;

public class VehiculoUtil {

    public static void validarPatente(String patente){

        if(patente == null){
            throw new VehiculoException("La patente no puede ser nula");
        }

        if(patente.isEmpty()){
            throw new VehiculoException("La patente no puede ser vacía");
        }

    }

}
