package com.besysoft.bootcamp.repository;

import com.besysoft.bootcamp.domain.entity.DetalleOrdenDeTrabajo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetalleOrdenDeTrabajoRepository extends JpaRepository<DetalleOrdenDeTrabajo, Long> {
}
