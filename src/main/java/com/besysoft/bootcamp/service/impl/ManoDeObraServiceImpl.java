package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.ManoDeObra;
import com.besysoft.bootcamp.exception.ManoDeObraException;
import com.besysoft.bootcamp.repository.IManoDeObraRepository;
import com.besysoft.bootcamp.service.IManoDeObraService;

import com.besysoft.bootcamp.util.ValidacionGeneralUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
public class ManoDeObraServiceImpl implements IManoDeObraService {

    private final IManoDeObraRepository manoDeObraRepository;

    public ManoDeObraServiceImpl(IManoDeObraRepository manoDeObraRepository) {
        this.manoDeObraRepository = manoDeObraRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public ManoDeObra crear(ManoDeObra manoDeObra) {
        if(manoDeObra == null){
            throw new ManoDeObraException("La Mano de Obra no puede ser nula");
        }

        return this.manoDeObraRepository.save(manoDeObra);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ManoDeObra> buscarPorId(Long id) {
        ValidacionGeneralUtil.validarId(id);
        return this.manoDeObraRepository.findById(id);
    }

}
