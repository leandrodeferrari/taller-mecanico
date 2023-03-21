package com.besysoft.bootcamp.dto.mapper;

import com.besysoft.bootcamp.domain.entity.Cliente;
import com.besysoft.bootcamp.domain.entity.Vehiculo;
import com.besysoft.bootcamp.dto.request.ClienteVehiculoInDto;
import com.besysoft.bootcamp.dto.response.ClienteVehiculoOutDto;

public interface IClienteVehiculoMapper {

    void updateToEntities(ClienteVehiculoInDto dto, Cliente cliente, Vehiculo vehiculo);
    void updateToEntityCliente(ClienteVehiculoInDto dto, Cliente cliente);
    void updateToEntityVehiculo(ClienteVehiculoInDto dto, Vehiculo vehiculo);
    ClienteVehiculoOutDto mapToDto(Cliente cliente, Vehiculo vehiculo, String info);

}
