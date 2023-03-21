package com.besysoft.bootcamp.repository;

import com.besysoft.bootcamp.domain.entity.Vehiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, Long> {

    boolean existsByPatente(String patente);
    Optional<Vehiculo> findByPatente(String patente);

}
