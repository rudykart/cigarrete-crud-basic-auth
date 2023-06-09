package com.rudykart.cigarrete.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rudykart.cigarrete.entities.Cigarrete;

public interface CigarreteRepository extends JpaRepository<Cigarrete, Long> {
    List<Cigarrete> findByNameContaining(String name);

    // List<Cigarrete> findByBranId(Long id);
}
