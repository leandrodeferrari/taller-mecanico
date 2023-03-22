package com.besysoft.bootcamp.service;

import com.besysoft.bootcamp.dto.response.ManoDeObraOutDto;

public interface IMecanicoService {

    ManoDeObraOutDto generarManoDeObra(Long mecanicoId, Long ordenDeTrabajoId);

}
