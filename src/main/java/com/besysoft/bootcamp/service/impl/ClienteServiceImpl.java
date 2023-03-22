package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.Cliente;
import com.besysoft.bootcamp.domain.entity.Vehiculo;
import com.besysoft.bootcamp.dto.mapper.IClienteVehiculoMapper;
import com.besysoft.bootcamp.dto.request.ClienteVehiculoInDto;
import com.besysoft.bootcamp.dto.response.ClienteVehiculoOutDto;
import com.besysoft.bootcamp.exception.ClienteVehiculoException;
import com.besysoft.bootcamp.repository.IClienteRepository;
import com.besysoft.bootcamp.service.IClienteService;
import com.besysoft.bootcamp.service.IVehiculoService;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
public class ClienteServiceImpl implements IClienteService {

    private final IClienteRepository clienteRepository;
    private final IClienteVehiculoMapper clienteVehiculoMapper;
    private final IVehiculoService vehiculoService;

    public ClienteServiceImpl(IClienteRepository clienteRepository,
                              IClienteVehiculoMapper clienteVehiculoMapper,
                              IVehiculoService vehiculoService) {
        this.clienteRepository = clienteRepository;
        this.clienteVehiculoMapper = clienteVehiculoMapper;
        this.vehiculoService = vehiculoService;
    }

    @Override
    @Transactional(readOnly = false)
    public ClienteVehiculoOutDto recibirClienteVehiculo(ClienteVehiculoInDto dto) {

        Optional<Vehiculo> optionalVehiculo = this.vehiculoService.buscarPorPatente(dto.getPatente());

        if(optionalVehiculo.isPresent()){

            // Validar datos del Cliente

            Vehiculo vehiculo = optionalVehiculo.get();

            boolean existeCliente = vehiculo.getClientes()
                    .stream()
                    .anyMatch(c -> c.getCorreoElectronico().equals(dto.getCorreoElectronico()));

            if(existeCliente) {

                Cliente cliente = this.clienteRepository
                        .findByCorreoElectronico(dto.getCorreoElectronico()).orElseThrow();

                String info = "Ya existían Cliente y Vehiculo";

                return this.clienteVehiculoMapper.mapToDto(cliente, vehiculo, info);

            } else {

                Cliente cliente = new Cliente();
                this.clienteVehiculoMapper.updateToEntityCliente(dto, cliente);

                if(cliente.getApellido() == null || cliente.getNombres() == null){
                    throw new ClienteVehiculoException("Si va a crear un cliente, nombres y apellido no pueden ser nulos");
                }

                Cliente clienteCreado = this.clienteRepository.save(cliente);
                clienteCreado.setVehiculos(List.of(vehiculo));
                vehiculo.getClientes().add(clienteCreado);

                String info = "Ya existía Vehiculo y se creó Cliente";

                return this.clienteVehiculoMapper.mapToDto(clienteCreado, vehiculo, info);

            }

        } else {

            Optional<Cliente> optionalCliente = this.clienteRepository
                    .findByCorreoElectronico(dto.getCorreoElectronico());

            if(optionalCliente.isPresent()){

                // Se vincula el vehiculo, al cliente

                Cliente cliente = optionalCliente.get();

                Vehiculo vehiculo = new Vehiculo();
                this.clienteVehiculoMapper.updateToEntityVehiculo(dto, vehiculo);

                Vehiculo vehiculoCreado = this.vehiculoService.crear(vehiculo);
                vehiculoCreado.setClientes(List.of(cliente));
                cliente.getVehiculos().add(vehiculoCreado);

                String info = "Ya existía cliente y se creó Vehículo";

                return this.clienteVehiculoMapper.mapToDto(cliente, vehiculoCreado, info);

            } else {

                // Crear cliente y vehiculo

                Cliente cliente = new Cliente();
                Vehiculo vehiculo = new Vehiculo();
                this.clienteVehiculoMapper.updateToEntities(dto, cliente, vehiculo);

                if(cliente.getApellido() == null || cliente.getNombres() == null){
                    throw new ClienteVehiculoException("Si va a crear un cliente, nombres y apellido no pueden ser nulos");
                }

                Cliente clienteCreado = this.clienteRepository.save(cliente);
                Vehiculo vehiculoCreado = this.vehiculoService.crear(vehiculo);
                clienteCreado.setVehiculos(List.of(vehiculoCreado));
                vehiculoCreado.setClientes(List.of(clienteCreado));

                String info = "Se creó Cliente y Vehículo";

                return this.clienteVehiculoMapper.mapToDto(clienteCreado, vehiculoCreado, info);

            }

        }

    }

}
