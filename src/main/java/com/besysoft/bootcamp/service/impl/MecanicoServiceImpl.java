package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.*;
import com.besysoft.bootcamp.domain.enums.ActivoEnum;
import com.besysoft.bootcamp.domain.enums.EstadoEnum;
import com.besysoft.bootcamp.dto.mapper.IManoDeObraMapper;
import com.besysoft.bootcamp.dto.mapper.IMecanicoMapper;
import com.besysoft.bootcamp.dto.mapper.IOrdenDeTrabajoMapper;
import com.besysoft.bootcamp.dto.mapper.IReparacionMapper;
import com.besysoft.bootcamp.dto.request.MecanicoInDto;
import com.besysoft.bootcamp.dto.request.ReparacionInDto;
import com.besysoft.bootcamp.dto.response.ManoDeObraOutDto;
import com.besysoft.bootcamp.dto.response.MecanicoOutDto;
import com.besysoft.bootcamp.dto.response.OrdenDeTrabajoOutDto;
import com.besysoft.bootcamp.dto.response.ReparacionOutDto;
import com.besysoft.bootcamp.exception.ManoDeObraException;
import com.besysoft.bootcamp.exception.MecanicoException;
import com.besysoft.bootcamp.exception.OrdenDeTrabajoException;
import com.besysoft.bootcamp.exception.RepuestoException;
import com.besysoft.bootcamp.repository.IMecanicoRepository;
import com.besysoft.bootcamp.service.*;
import com.besysoft.bootcamp.util.MecanicoUtil;
import com.besysoft.bootcamp.util.ValidacionGeneralUtil;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
public class MecanicoServiceImpl implements IMecanicoService {

    private final IDetalleOrdenDeTrabajoService detalleService;
    private final IManoDeObraMapper manoDeObraMapper;
    private final IManoDeObraService manoDeObraService;
    private final IMecanicoMapper mecanicoMapper;
    private final IMecanicoRepository mecanicoRepository;
    private final IOrdenDeTrabajoMapper ordenDeTrabajoMapper;
    private final IOrdenDeTrabajoService ordenDeTrabajoService;
    private final IReparacionMapper reparacionMapper;
    private final IRepuestoService repuestoService;

    public MecanicoServiceImpl(IDetalleOrdenDeTrabajoService detalleService,
                               IManoDeObraMapper manoDeObraMapper,
                               IManoDeObraService manoDeObraService,
                               IMecanicoMapper mecanicoMapper,
                               IMecanicoRepository mecanicoRepository,
                               IOrdenDeTrabajoMapper ordenDeTrabajoMapper,
                               IOrdenDeTrabajoService ordenDeTrabajoService,
                               IReparacionMapper reparacionMapper,
                               IRepuestoService repuestoService) {
        this.detalleService = detalleService;
        this.manoDeObraMapper = manoDeObraMapper;
        this.manoDeObraService = manoDeObraService;
        this.mecanicoMapper = mecanicoMapper;
        this.mecanicoRepository = mecanicoRepository;
        this.ordenDeTrabajoMapper = ordenDeTrabajoMapper;
        this.ordenDeTrabajoService = ordenDeTrabajoService;
        this.reparacionMapper = reparacionMapper;
        this.repuestoService = repuestoService;
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

    @Override
    @Transactional(readOnly = false)
    public OrdenDeTrabajoOutDto iniciarReparacion(Long ordenDeTrabajoId) {

        ValidacionGeneralUtil.validarId(ordenDeTrabajoId);

        Optional<OrdenDeTrabajo> optionalOrdenDeTrabajo = this.ordenDeTrabajoService
                .buscarPorId(ordenDeTrabajoId);

        if(optionalOrdenDeTrabajo.isEmpty()){
            throw new OrdenDeTrabajoException("No existe Orden de Trabajo con ese ID");
        }

        OrdenDeTrabajo ordenDeTrabajo = optionalOrdenDeTrabajo.get();

        if(!ordenDeTrabajo.getEstado().equals(EstadoEnum.CREADA.valor)){
            throw new OrdenDeTrabajoException("La Orden de Trabajo, no tiene el estado adecuado (Creada)");
        }

        ordenDeTrabajo.setEstado(EstadoEnum.EN_REPARACION.valor);

        return this.ordenDeTrabajoMapper.mapToDto(ordenDeTrabajo);

    }

    @Override
    @Transactional(readOnly = false)
    public ReparacionOutDto finalizarReparacion(ReparacionInDto dto,
                                                Long manoDeObraId,
                                                Long ordeDeTrabajoId) {

        ValidacionGeneralUtil.validarId(manoDeObraId);
        ValidacionGeneralUtil.validarId(ordeDeTrabajoId);

        Optional<ManoDeObra> optionalManoDeObra = this.manoDeObraService
                .buscarPorId(manoDeObraId);

        if(optionalManoDeObra.isEmpty()){
            throw new ManoDeObraException("No existe Mano de Obra con ese ID");
        }

        Optional<OrdenDeTrabajo> optionalOrdenDeTrabajo = this.ordenDeTrabajoService
                .buscarPorId(ordeDeTrabajoId);

        if(optionalOrdenDeTrabajo.isEmpty()){
            throw new OrdenDeTrabajoException("No existe Orden de Trabajo con ese ID");
        }

        OrdenDeTrabajo ordenDeTrabajo = optionalOrdenDeTrabajo.get();

        if(!ordenDeTrabajo.getEstado().equals(EstadoEnum.EN_REPARACION.valor)){
            throw new OrdenDeTrabajoException("La Orden de Trabajo, no tiene el estado adecuado (En reparación)");
        }

        ManoDeObra manoDeObra = optionalManoDeObra.get();

        if(!manoDeObra.getOrdenDeTrabajo().getId().equals(ordenDeTrabajo.getId())){
            throw new ManoDeObraException("La Mano de Obra no está relacionada a la Orden de Trabajo");
        }

        List<Repuesto> repuestos = this.repuestoService.buscarTodosPorId(dto.getRepuestosId());

        if(repuestos.isEmpty()){
            throw new RepuestoException("No existen repuestos con esos IDs");
        }

        if(dto.getRepuestosId().size() != repuestos.size()){
            throw new RepuestoException("No existe la misma cantidad de repuestos, que de IDs suministrados");
        }

        manoDeObra.setDetalle(dto.getDetalle());
        manoDeObra.setDuracionEnHoras(LocalTime.parse(dto.getDuracionEnHoras()));

        repuestos.forEach(r -> {
            DetalleOrdenDeTrabajo detalle = new DetalleOrdenDeTrabajo();
            detalle.setRepuesto(r);
            detalle.setOrdenDeTrabajo(ordenDeTrabajo);
            DetalleOrdenDeTrabajo detalleCreado = this.detalleService.crear(detalle);
            r.getDetallesDeOrdenesDeTrabajo().add(detalleCreado);
            ordenDeTrabajo.getDetallesDeOrdenesDeTrabajo().add(detalleCreado);
        });

        ordenDeTrabajo.setEstado(EstadoEnum.PARA_FACTURAR.valor);

        return this.reparacionMapper.mapToDto(manoDeObra, repuestos);

    }

}
