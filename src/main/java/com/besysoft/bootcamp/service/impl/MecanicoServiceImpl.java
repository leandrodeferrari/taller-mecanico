package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.ManoDeObra;
import com.besysoft.bootcamp.domain.entity.Mecanico;
import com.besysoft.bootcamp.domain.entity.OrdenDeTrabajo;
import com.besysoft.bootcamp.domain.enums.ActivoEnum;
import com.besysoft.bootcamp.dto.mapper.IManoDeObraMapper;
import com.besysoft.bootcamp.dto.mapper.IMecanicoMapper;
import com.besysoft.bootcamp.dto.request.MecanicoInDto;
import com.besysoft.bootcamp.dto.response.ManoDeObraOutDto;
import com.besysoft.bootcamp.dto.response.MecanicoOutDto;
import com.besysoft.bootcamp.exception.MecanicoException;
import com.besysoft.bootcamp.exception.OrdenDeTrabajoException;
import com.besysoft.bootcamp.repository.IMecanicoRepository;
import com.besysoft.bootcamp.service.IManoDeObraService;
import com.besysoft.bootcamp.service.IMecanicoService;
import com.besysoft.bootcamp.service.IOrdenDeTrabajoService;
import com.besysoft.bootcamp.util.MecanicoUtil;
import com.besysoft.bootcamp.util.ValidacionGeneralUtil;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
public class MecanicoServiceImpl implements IMecanicoService {

    private final IManoDeObraMapper manoDeObraMapper;
    private final IManoDeObraService manoDeObraService;
    private final IMecanicoMapper mecanicoMapper;
    private final IMecanicoRepository mecanicoRepository;
    private final IOrdenDeTrabajoService ordenDeTrabajoService;

    public MecanicoServiceImpl(IManoDeObraMapper manoDeObraMapper,
                               IManoDeObraService manoDeObraService,
                               IMecanicoMapper mecanicoMapper,
                               IMecanicoRepository mecanicoRepository,
                               IOrdenDeTrabajoService ordenDeTrabajoService) {
        this.manoDeObraMapper = manoDeObraMapper;
        this.manoDeObraService = manoDeObraService;
        this.mecanicoMapper = mecanicoMapper;
        this.mecanicoRepository = mecanicoRepository;
        this.ordenDeTrabajoService = ordenDeTrabajoService;
    }

    @Override
    @Transactional(readOnly = false)
    public ManoDeObraOutDto generarManoDeObra(Long mecanicoId, Long ordenDeTrabajoId) {

        ValidacionGeneralUtil.validarId(mecanicoId);
        ValidacionGeneralUtil.validarId(ordenDeTrabajoId);

        Optional<Mecanico> optionalMecanico = this.mecanicoRepository.findById(mecanicoId);

        if(optionalMecanico.isEmpty()){
            throw new MecanicoException("No existe Mecánico con ese ID");
        }

        Mecanico mecanico = optionalMecanico.get();

        if(mecanico.getActivo().equals(ActivoEnum.FALSO.valor)){
            throw new MecanicoException("El mecánico no está activo");
        }

        Optional<OrdenDeTrabajo> optionalOrdenDeTrabajo = this.ordenDeTrabajoService
                .buscarPorId(ordenDeTrabajoId);

        if(optionalOrdenDeTrabajo.isEmpty()){
            throw new OrdenDeTrabajoException("No existe Orden de Trabajo con ese ID");
        }

        OrdenDeTrabajo ordenDeTrabajo = optionalOrdenDeTrabajo.get();

        ManoDeObra manoDeObra = new ManoDeObra();
        manoDeObra.setOrdenDeTrabajo(ordenDeTrabajo);
        manoDeObra.setMecanico(mecanico);

        ManoDeObra manoDeObraCreada = this.manoDeObraService.crear(manoDeObra);
        ordenDeTrabajo.setManosDeObra(Set.of(manoDeObraCreada));
        mecanico.setManosDeObra(Set.of(manoDeObraCreada));

        return this.manoDeObraMapper.mapToDto(manoDeObraCreada);

    }

    @Override
    @Transactional(readOnly = false)
    public MecanicoOutDto crear(MecanicoInDto dto) {
        MecanicoUtil.validarActivo(dto.getActivo());
        Mecanico mecanico = this.mecanicoMapper.mapToEntity(dto);
        return this.mecanicoMapper.mapToDto(this.mecanicoRepository.save(mecanico));
    }

}
