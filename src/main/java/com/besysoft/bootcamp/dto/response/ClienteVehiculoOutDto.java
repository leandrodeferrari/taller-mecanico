package com.besysoft.bootcamp.dto.response;

import lombok.Data;

@Data
public class ClienteVehiculoOutDto {

    private Long clienteId;
    private String nombres;
    private String apellido;
    private String correoElectronico;
    private Long vehiculoId;
    private String patente;
    private String info;

}
