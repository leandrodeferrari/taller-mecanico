package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.entity.Cliente;
import com.besysoft.bootcamp.domain.entity.Vehiculo;

import java.util.List;

public class VehiculoTestUtil {

    public static Vehiculo generarVehiculoSinId(){

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setAnio(1900);
        vehiculo.setMarca("Renault");
        vehiculo.setColor("Negro");
        vehiculo.setPatente("ABC123");
        vehiculo.setModelo("Fuego");

        return vehiculo;

    }

    public static Vehiculo generarVehiculo(){

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(1L);
        vehiculo.setAnio(1900);
        vehiculo.setMarca("Renault");
        vehiculo.setColor("Negro");
        vehiculo.setPatente("ABC123");
        vehiculo.setModelo("Fuego");

        Cliente cliente = new Cliente();
        cliente.setCorreoElectronico("leando_gomez@hotmail.com");

        vehiculo.setClientes(List.of(cliente));

        return vehiculo;

    }

}
