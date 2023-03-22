package com.besysoft.bootcamp.service;

import com.besysoft.bootcamp.dto.request.ClienteVehiculoInDto;
import com.besysoft.bootcamp.dto.response.ClienteVehiculoOutDto;

public interface IClienteService {

    ClienteVehiculoOutDto recibirClienteVehiculo(ClienteVehiculoInDto dto);

}
