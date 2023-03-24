package com.besysoft.bootcamp.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ReparacionOutDto {

    ManoDeObraOutDto manoDeObra;
    List<RepuestoOutDto> repuestos;

}
