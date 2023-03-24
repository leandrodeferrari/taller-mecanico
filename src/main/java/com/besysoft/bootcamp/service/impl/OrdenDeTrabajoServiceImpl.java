package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.Empleado;
import com.besysoft.bootcamp.domain.entity.OrdenDeTrabajo;
import com.besysoft.bootcamp.domain.enums.EstadoEnum;
import com.besysoft.bootcamp.domain.enums.TipoDeEmpleadoEnum;
import com.besysoft.bootcamp.dto.mapper.IOrdenDeTrabajoMapper;
import com.besysoft.bootcamp.dto.request.OrdenDeTrabajoInDto;
import com.besysoft.bootcamp.dto.response.OrdenDeTrabajoOutDto;
import com.besysoft.bootcamp.exception.EmpleadoException;
import com.besysoft.bootcamp.exception.OrdenDeTrabajoException;
import com.besysoft.bootcamp.repository.IOrdenDeTrabajoRepository;
import com.besysoft.bootcamp.service.IEmpleadoService;
import com.besysoft.bootcamp.service.IOrdenDeTrabajoService;
import com.besysoft.bootcamp.util.ValidacionGeneralUtil;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
public class OrdenDeTrabajoServiceImpl implements IOrdenDeTrabajoService {

    private final IEmpleadoService empleadoService;
    private final IOrdenDeTrabajoMapper ordenDeTrabajoMapper;
    private final IOrdenDeTrabajoRepository ordenDeTrabajoRepository;

    public OrdenDeTrabajoServiceImpl(IEmpleadoService empleadoService ,
                                     IOrdenDeTrabajoMapper ordenDeTrabajoMapper,
                                     IOrdenDeTrabajoRepository ordenDeTrabajoRepository) {
        this.empleadoService = empleadoService;
        this.ordenDeTrabajoMapper = ordenDeTrabajoMapper;
        this.ordenDeTrabajoRepository = ordenDeTrabajoRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public OrdenDeTrabajoOutDto crear(OrdenDeTrabajoInDto dto) {
        if(dto == null){
            throw new OrdenDeTrabajoException("El valor ingresado, puede ser nulo");
        }

        Optional<Empleado> optionalRecepcionista = this.empleadoService
                .buscarPorId(dto.getRecepcionistaId());

        if(optionalRecepcionista.isEmpty()){
            throw new EmpleadoException("No existe Empleado con ese ID");
        }

        Empleado recepcionista = optionalRecepcionista.get();

        if(!recepcionista.getTipoDeEmpleado().equals(TipoDeEmpleadoEnum.RECEPCIONISTA.valor)){
            throw new EmpleadoException("Empleado inválido. No es recepcionista");
        }

        OrdenDeTrabajo ordenDeTrabajo = this.ordenDeTrabajoMapper.mapToEntity(dto);
        ordenDeTrabajo.setRecepcionista(recepcionista);
        return this.ordenDeTrabajoMapper.mapToDto(this.ordenDeTrabajoRepository.save(ordenDeTrabajo));
    }

    @Override
    @Transactional(readOnly = false)
    public OrdenDeTrabajoOutDto entregarVehiculo(Long id) {

        ValidacionGeneralUtil.validarId(id);

        Optional<OrdenDeTrabajo> optional = this.ordenDeTrabajoRepository.findById(id);

        if(optional.isEmpty()){
            throw new OrdenDeTrabajoException("No existe Orden de Trabajo con ese ID");
        }

        OrdenDeTrabajo ordenDeTrabajo = optional.get();

        if(!ordenDeTrabajo.getEstado().equals(EstadoEnum.FACTURADA.valor)){
            throw new OrdenDeTrabajoException("La Orden de Trabajo, no tiene el estado adecuado (Facturada)");
        }

        ordenDeTrabajo.setEstado(EstadoEnum.CERRADA.valor);

        return this.ordenDeTrabajoMapper.mapToDto(ordenDeTrabajo);

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrdenDeTrabajo> buscarPorId(Long id) {
        ValidacionGeneralUtil.validarId(id);
        return this.ordenDeTrabajoRepository.findById(id);
    }

}
