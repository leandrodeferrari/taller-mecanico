package com.besysoft.bootcamp.repository;

import com.besysoft.bootcamp.domain.entity.Mecanico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMecanicoRepository extends JpaRepository<Mecanico, Long> {
}
