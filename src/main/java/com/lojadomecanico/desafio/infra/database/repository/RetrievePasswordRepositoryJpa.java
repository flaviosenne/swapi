package com.lojadomecanico.desafio.infra.database.repository;

import com.lojadomecanico.desafio.infra.database.entity.RetrievePasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RetrievePasswordRepositoryJpa extends JpaRepository<RetrievePasswordEntity, Integer> {


    @Query("select r from RetrievePasswordEntity r where r.code = :code")
    Optional<RetrievePasswordEntity> findByCode(String code);

    @Query("select r from RetrievePasswordEntity r " +
            "join r.user u " +
            "where u.id = :userId and r.isValid = 1")
    Optional<RetrievePasswordEntity> findByUserId(Integer userId);
}
