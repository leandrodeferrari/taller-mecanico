package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.entity.Empleado;
import com.besysoft.bootcamp.domain.enums.TipoDeEmpleadoEnum;

public class EmpleadoTestUtil {

    public static Empleado generarRecepcionista(){

        Empleado recepcionista = new Empleado();
        recepcionista.setId(1L);
        recepcionista.setTipoDeEmpleado(TipoDeEmpleadoEnum.RECEPCIONISTA.valor);

        return recepcionista;

    }

}
