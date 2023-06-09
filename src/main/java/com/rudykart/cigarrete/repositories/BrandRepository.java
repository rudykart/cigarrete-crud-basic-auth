package com.rudykart.cigarrete.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rudykart.cigarrete.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> findByNameContaining(String name);
}
