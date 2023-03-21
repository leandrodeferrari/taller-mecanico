package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.exception.ClienteException;

public class ClienteUtil {

    public static void validarCorreoElectronico(String correoElectronico){

        if(correoElectronico == null){
            throw new ClienteException("El correo electrónico no puede ser nulo");
        }

        if(correoElectronico.isEmpty()){
            throw new ClienteException("El correo electrónico no puede ser vacío");
        }

        if(!correoElectronico.contains("@")){
            throw new ClienteException("El correo electrónico, no es válido. Necesita contener un '@' (arroba)");
        }

    }

}
