package com.besysoft.bootcamp.service;

import com.besysoft.bootcamp.domain.entity.ManoDeObra;

import java.util.Optional;

public interface IManoDeObraService {

    ManoDeObra crear(ManoDeObra manoDeObra);
    Optional<ManoDeObra> buscarPorId(Long id);

}
