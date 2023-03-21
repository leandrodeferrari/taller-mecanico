package com.besysoft.bootcamp.dto.mapper.impl;

import com.besysoft.bootcamp.domain.entity.Cliente;
import com.besysoft.bootcamp.domain.entity.Domicilio;
import com.besysoft.bootcamp.domain.entity.Vehiculo;
import com.besysoft.bootcamp.dto.mapper.IClienteVehiculoMapper;
import com.besysoft.bootcamp.dto.request.ClienteVehiculoInDto;
import com.besysoft.bootcamp.dto.response.ClienteVehiculoOutDto;
import com.besysoft.bootcamp.exception.ClienteVehiculoException;

import org.springframework.stereotype.Component;

@Component
public class ClienteVehiculoMapperImpl implements IClienteVehiculoMapper {

    @Override
    public void updateToEntities(ClienteVehiculoInDto dto, Cliente cliente, Vehiculo vehiculo) {
        if(dto == null){
            throw new ClienteVehiculoException("No se pudo mapear, debido a que el valor es nulo");
        }

        vehiculo.setPatente(dto.getPatente());
        vehiculo.setAnio(dto.getAnio());
        vehiculo.setColor(dto.getColor());
        vehiculo.setMarca(dto.getMarca());
        vehiculo.setModelo(dto.getModelo());

        cliente.setCorreoElectronico(dto.getCorreoElectronico());
        cliente.setNombres(dto.getNombres());
        cliente.setApellido(dto.getApellido());
        cliente.setCelular(dto.getCelular());
        cliente.setTelefonoDeLinea(dto.getTelefonoDeLinea());

        Domicilio domicilio = new Domicilio();
        domicilio.setCalle(dto.getCalle());
        domicilio.setCodigoPostal(dto.getCodigoPostal());
        domicilio.setDepartamento(dto.getDepartamento());
        domicilio.setLocalidad(dto.getLocalidad());
        domicilio.setNumero(dto.getNumero());
        domicilio.setPiso(dto.getPiso());

        cliente.setDomicilio(domicilio);

    }

    @Override
    public void updateToEntityCliente(ClienteVehiculoInDto dto, Cliente cliente) {
        if(dto == null){
            throw new ClienteVehiculoException("No se pudo mapear, debido a que el valor es nulo");
        }

        cliente.setCorreoElectronico(dto.getCorreoElectronico());
        cliente.setNombres(dto.getNombres());
        cliente.setApellido(dto.getApellido());
        cliente.setCelular(dto.getCelular());
        cliente.setTelefonoDeLinea(dto.getTelefonoDeLinea());

        Domicilio domicilio = new Domicilio();
        domicilio.setCalle(dto.getCalle());
        domicilio.setCodigoPostal(dto.getCodigoPostal());
        domicilio.setDepartamento(dto.getDepartamento());
        domicilio.setLocalidad(dto.getLocalidad());
        domicilio.setNumero(dto.getNumero());
        domicilio.setPiso(dto.getPiso());

        cliente.setDomicilio(domicilio);

    }

    @Override
    public void updateToEntityVehiculo(ClienteVehiculoInDto dto, Vehiculo vehiculo) {
        if(dto == null){
            throw new ClienteVehiculoException("No se pudo mapear, debido a que el valor es nulo");
        }

        vehiculo.setPatente(dto.getPatente());
        vehiculo.setAnio(dto.getAnio());
        vehiculo.setColor(dto.getColor());
        vehiculo.setMarca(dto.getMarca());
        vehiculo.setModelo(dto.getModelo());

    }

    @Override
    public ClienteVehiculoOutDto mapToDto(Cliente cliente, Vehiculo vehiculo, String info) {
        if(cliente == null || vehiculo == null){
            throw new ClienteVehiculoException("No se pudo mapear, debido a que alguna entidad, o ambas, son nulas");
        }

        ClienteVehiculoOutDto dto = new ClienteVehiculoOutDto();
        dto.setClienteId(cliente.getId());
        dto.setNombres(cliente.getNombres());
        dto.setApellido(cliente.getApellido());
        dto.setCorreoElectronico(cliente.getCorreoElectronico());
        dto.setVehiculoId(vehiculo.getId());
        dto.setPatente(vehiculo.getPatente());
        dto.setInfo(info);

        return dto;

    }

}
