package com.rudykart.cigarrete.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rudykart.cigarrete.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
