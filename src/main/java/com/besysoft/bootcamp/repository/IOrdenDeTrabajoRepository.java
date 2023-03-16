package com.besysoft.bootcamp.repository;

import com.besysoft.bootcamp.domain.entity.OrdenDeTrabajo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdenDeTrabajoRepository extends JpaRepository<OrdenDeTrabajo, Long> {
}
