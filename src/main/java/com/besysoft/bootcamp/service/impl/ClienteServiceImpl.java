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

import com.besysoft.bootcamp.util.ClienteUtil;
import com.besysoft.bootcamp.util.VehiculoUtil;
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

    private static final String INFO_SE_CREAN_AMBOS = "Se creó Cliente y Vehículo";
    private static final String INFO_SE_CREO_VEHICULO = "Ya existía cliente y se creó Vehículo";
    private static final String INFO_SE_CREO_CLIENTE = "Ya existía Vehiculo y se creó Cliente";
    private static final String INFO_YA_EXISTIAN = "Ya existían Cliente y Vehiculo";

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

        ClienteUtil.validarCorreoElectronico(dto.getCorreoElectronico());
        VehiculoUtil.validarPatente(dto.getPatente());

        // Valida existencia de Vehiculo, por patente
        Optional<Vehiculo> optionalVehiculo = this.vehiculoService.buscarPorPatente(dto.getPatente());

        if(optionalVehiculo.isPresent()){

            // Si existe Vehiculo, se validan los datos del Cliente
            Vehiculo vehiculo = optionalVehiculo.get();

            boolean existeCliente = vehiculo.getClientes()
                    .stream()
                    .anyMatch(c -> c.getCorreoElectronico().equals(dto.getCorreoElectronico()));

            // Si existe Cliente, ya existian ambas entidades
            if(existeCliente) {

                Cliente cliente = this.clienteRepository
                        .findByCorreoElectronico(dto.getCorreoElectronico()).orElseThrow();

                return this.clienteVehiculoMapper.mapToDto(cliente, vehiculo, INFO_YA_EXISTIAN);

                //Si no existe Cliente, se crea y se vincula a Vehiculo
            } else {

                Cliente cliente = new Cliente();
                this.clienteVehiculoMapper.updateToEntityCliente(dto, cliente);

                validarNombresApellido(cliente.getNombres(), cliente.getApellido());

                Cliente clienteCreado = this.clienteRepository.save(cliente);
                clienteCreado.setVehiculos(List.of(vehiculo));
                vehiculo.getClientes().add(clienteCreado);

                return this.clienteVehiculoMapper.mapToDto(clienteCreado, vehiculo, INFO_SE_CREO_CLIENTE);

            }

            //Si no existe Vehiculo, se valida Cliente por correo electronico
        } else {

            Optional<Cliente> optionalCliente = this.clienteRepository
                    .findByCorreoElectronico(dto.getCorreoElectronico());

            //Si existe Cliente, se crea Vehiculo y se vinculan
            if(optionalCliente.isPresent()){

                Cliente cliente = optionalCliente.get();

                Vehiculo vehiculo = new Vehiculo();
                this.clienteVehiculoMapper.updateToEntityVehiculo(dto, vehiculo);

                Vehiculo vehiculoCreado = this.vehiculoService.crear(vehiculo);
                vehiculoCreado.setClientes(List.of(cliente));
                cliente.getVehiculos().add(vehiculoCreado);

                return this.clienteVehiculoMapper.mapToDto(cliente, vehiculoCreado, INFO_SE_CREO_VEHICULO);

                // Si no existe Cliente, se crean ambos y se vinculan
            } else {

                Cliente cliente = new Cliente();
                Vehiculo vehiculo = new Vehiculo();
                this.clienteVehiculoMapper.updateToEntities(dto, cliente, vehiculo);

                validarNombresApellido(cliente.getNombres(), cliente.getApellido());

                Cliente clienteCreado = this.clienteRepository.save(cliente);
                Vehiculo vehiculoCreado = this.vehiculoService.crear(vehiculo);
                clienteCreado.setVehiculos(List.of(vehiculoCreado));
                vehiculoCreado.setClientes(List.of(clienteCreado));

                return this.clienteVehiculoMapper.mapToDto(clienteCreado, vehiculoCreado, INFO_SE_CREAN_AMBOS);

            }

        }

    }

    private void validarNombresApellido(String nombres, String apellido){
        if(nombres == null || apellido == null){
            throw new ClienteVehiculoException("Si va a crear un cliente, nombres y apellido no pueden ser nulos");
        }
    }

}
