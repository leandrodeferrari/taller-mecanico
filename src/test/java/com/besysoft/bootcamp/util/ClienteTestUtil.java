package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.dto.request.ClienteVehiculoInDto;
import com.besysoft.bootcamp.dto.response.ClienteVehiculoOutDto;

public class ClienteTestUtil {

    public static ClienteVehiculoInDto generarClienteVehiculoInDto(){

        ClienteVehiculoInDto dto = new ClienteVehiculoInDto();
        dto.setAnio(1900);
        dto.setApellido("Gomez");
        dto.setCalle("Carrasco");
        dto.setColor("Negro");
        dto.setCelular("11-5656-3434");
        dto.setMarca("Renault");
        dto.setDepartamento("3");
        dto.setLocalidad("CABA");
        dto.setCodigoPostal("1448");
        dto.setCorreoElectronico("leando_gomez@hotmail.com");
        dto.setModelo("Fuego");
        dto.setNombres("Roberto Gonzalo");
        dto.setPatente("ABC123");
        dto.setNumero("456");
        dto.setPiso("2");
        dto.setTelefonoDeLinea("3360-3434");

        return dto;

    }

    public static ClienteVehiculoOutDto generarClienteVehiculoOutDto(){

        ClienteVehiculoOutDto dto = new ClienteVehiculoOutDto();
        dto.setClienteId(1L);
        dto.setNombres("Roberto Gonzalo");
        dto.setApellido("Gomez");
        dto.setCorreoElectronico("leando_gomez@hotmail.com");
        dto.setVehiculoId(1L);
        dto.setPatente("ABC123");
        dto.setInfo("Se creó vehiculo y Cliente");

        return dto;

    }

}
