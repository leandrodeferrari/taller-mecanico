package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.DetalleOrdenDeTrabajo;
import com.besysoft.bootcamp.domain.entity.Empleado;
import com.besysoft.bootcamp.domain.entity.OrdenDeTrabajo;
import com.besysoft.bootcamp.domain.enums.EstadoEnum;
import com.besysoft.bootcamp.domain.enums.TipoDeEmpleadoEnum;
import com.besysoft.bootcamp.dto.mapper.IFacturacionMapper;
import com.besysoft.bootcamp.dto.mapper.IOrdenDeTrabajoMapper;
import com.besysoft.bootcamp.dto.request.FacturacionInDto;
import com.besysoft.bootcamp.dto.request.OrdenDeTrabajoInDto;
import com.besysoft.bootcamp.dto.response.FacturacionOutDto;
import com.besysoft.bootcamp.dto.response.OrdenDeTrabajoOutDto;
import com.besysoft.bootcamp.exception.DetalleOrdenDeTrabajoException;
import com.besysoft.bootcamp.exception.EmpleadoException;
import com.besysoft.bootcamp.exception.OrdenDeTrabajoException;
import com.besysoft.bootcamp.repository.IOrdenDeTrabajoRepository;
import com.besysoft.bootcamp.service.IDetalleOrdenDeTrabajoService;
import com.besysoft.bootcamp.service.IEmpleadoService;
import com.besysoft.bootcamp.service.IOrdenDeTrabajoService;
import com.besysoft.bootcamp.util.ValidacionGeneralUtil;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
public class OrdenDeTrabajoServiceImpl implements IOrdenDeTrabajoService {

    private final IDetalleOrdenDeTrabajoService detalleService;
    private final IEmpleadoService empleadoService;
    private final IFacturacionMapper facturacionMapper;
    private final IOrdenDeTrabajoMapper ordenDeTrabajoMapper;
    private final IOrdenDeTrabajoRepository ordenDeTrabajoRepository;

    public OrdenDeTrabajoServiceImpl(IDetalleOrdenDeTrabajoService detalleService,
                                     IEmpleadoService empleadoService,
                                     IFacturacionMapper facturacionMapper,
                                     IOrdenDeTrabajoMapper ordenDeTrabajoMapper,
                                     IOrdenDeTrabajoRepository ordenDeTrabajoRepository) {
        this.detalleService = detalleService;
        this.empleadoService = empleadoService;
        this.facturacionMapper = facturacionMapper;
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

    @Override
    @Transactional(readOnly = false)
    public FacturacionOutDto facturar(FacturacionInDto dto, Long ordenDeTrabajoId, Long administrativoId) {

        ValidacionGeneralUtil.validarId(ordenDeTrabajoId);
        ValidacionGeneralUtil.validarId(administrativoId);

        Optional<OrdenDeTrabajo> optionalOrdenDeTrabajo = this.ordenDeTrabajoRepository
                .findById(ordenDeTrabajoId);

        if(optionalOrdenDeTrabajo.isEmpty()){
            throw new OrdenDeTrabajoException("No existe Orden de Trabajo con ese ID");
        }

        OrdenDeTrabajo ordenDeTrabajo = optionalOrdenDeTrabajo.get();

        if(!ordenDeTrabajo.getEstado().equals(EstadoEnum.PARA_FACTURAR.valor)){
            throw new OrdenDeTrabajoException("La Orden de Trabajo, no tiene el estado adecuado (Para facturar)");
        }

        Optional<Empleado> optionalAdmin = this.empleadoService.buscarPorId(administrativoId);

        if(optionalAdmin.isEmpty()){
            throw new EmpleadoException("No existe empleado con ese ID");
        }

        Empleado administrativo = optionalAdmin.get();

        if(!administrativo.getTipoDeEmpleado().equals(TipoDeEmpleadoEnum.ADMINISTRATIVO.valor)){
            throw new EmpleadoException("Empleado inválido. No es administrativo");
        }

        List<BigDecimal> valoresTotales = new ArrayList<>();

        dto.getDetalles().forEach(d -> {
            Optional<DetalleOrdenDeTrabajo> optional = this.detalleService.buscarPorId(d.getDeatalleId());

            if(optional.isEmpty()){
                throw new DetalleOrdenDeTrabajoException("No existe Detalle con ese ID");
            }

            DetalleOrdenDeTrabajo detalle = optional.get();

            if(!detalle.getOrdenDeTrabajo().getId().equals(ordenDeTrabajo.getId())){
                throw new DetalleOrdenDeTrabajoException("El detalle que ha ingresado, no es de la Orden de Trabajo");
            }

            detalle.setCantidad(d.getCantidad());

            BigDecimal valorTotal = detalle.getRepuesto().getValor()
                    .multiply(new BigDecimal(d.getCantidad().toString()));

            detalle.setValorTotal(valorTotal);
            valoresTotales.add(valorTotal);

        });

        BigDecimal importeTotal = sumarValores(valoresTotales);

        ordenDeTrabajo.setEstado(EstadoEnum.FACTURADA.valor);
        ordenDeTrabajo.setAdministrativo(administrativo);
        ordenDeTrabajo.setFechaDelPago(LocalDateTime.now());
        ordenDeTrabajo.setCantidadDeCuotas(dto.getCantidadDeCuotas());
        ordenDeTrabajo.setFormaDePago(dto.getFormaDePago());
        ordenDeTrabajo.setImporteTotal(importeTotal);
        ordenDeTrabajo.setTipoDeTarjeta(dto.getTipoDeTarjeta());

        return this.facturacionMapper.mapToDto(ordenDeTrabajo);

    }

    private BigDecimal sumarValores(List<BigDecimal> valores) {

        BigDecimal resultado = BigDecimal.ZERO;

        for (BigDecimal valor : valores) {
            if(valor!=null){
                resultado = resultado.add(valor);
            }

        }

        return resultado;

    }

}
