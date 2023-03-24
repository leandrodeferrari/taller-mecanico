package com.besysoft.bootcamp.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ReparacionOutDto {

    private ManoDeObraOutDto manoDeObra;
    private List<RepuestoOutDto> repuestos;
    private String finDeReparacion;

}
