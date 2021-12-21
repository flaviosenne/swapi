package com.lojadomecanico.desafio.infra.database.repository;

import com.lojadomecanico.desafio.infra.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepositoryJpa extends JpaRepository<UserEntity, Integer> {

    @Query("select u from UserEntity u where u.email = :email and u.isActive = 1")
    Optional<UserEntity> findByEmail(String email);
}
