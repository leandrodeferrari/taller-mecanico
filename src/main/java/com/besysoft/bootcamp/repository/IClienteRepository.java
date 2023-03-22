package com.besysoft.bootcamp.repository;

import com.besysoft.bootcamp.domain.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCorreoElectronico(String correoElectronico);

}
