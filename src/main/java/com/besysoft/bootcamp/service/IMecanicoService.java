package com.besysoft.bootcamp.service;

import com.besysoft.bootcamp.dto.request.MecanicoInDto;
import com.besysoft.bootcamp.dto.request.ReparacionInDto;
import com.besysoft.bootcamp.dto.response.ManoDeObraOutDto;
import com.besysoft.bootcamp.dto.response.MecanicoOutDto;
import com.besysoft.bootcamp.dto.response.OrdenDeTrabajoOutDto;
import com.besysoft.bootcamp.dto.response.ReparacionOutDto;

public interface IMecanicoService {

    ManoDeObraOutDto generarManoDeObra(Long mecanicoId, Long ordenDeTrabajoId);
    MecanicoOutDto crear(MecanicoInDto dto);
    OrdenDeTrabajoOutDto iniciarReparacion(Long ordenDeTrabajoId);
    ReparacionOutDto finalizarReparacion(ReparacionInDto dto, Long manoDeObraId, Long ordenDeTrabajoId);

}
