package com.besysoft.bootcamp.repository;

import com.besysoft.bootcamp.domain.entity.ManoDeObra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IManoDeObraRepository extends JpaRepository<ManoDeObra, Long> {
}
