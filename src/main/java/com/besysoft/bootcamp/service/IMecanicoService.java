package com.besysoft.bootcamp.service;

import com.besysoft.bootcamp.dto.request.MecanicoInDto;
import com.besysoft.bootcamp.dto.response.ManoDeObraOutDto;
import com.besysoft.bootcamp.dto.response.MecanicoOutDto;

public interface IMecanicoService {

    ManoDeObraOutDto generarManoDeObra(Long mecanicoId, Long ordenDeTrabajoId);
    MecanicoOutDto crear(MecanicoInDto dto);

}
