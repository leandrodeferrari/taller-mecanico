package com.besysoft.bootcamp.repository;

import com.besysoft.bootcamp.domain.entity.Repuesto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepuestoRepository extends JpaRepository<Repuesto, Long> {
}
