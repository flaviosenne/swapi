package com.lojadomecanico.desafio.infra.database.repository.impl;

import com.lojadomecanico.desafio.domain.entities.RetrievePassword;
import com.lojadomecanico.desafio.domain.repositories.RetrievePasswordRepository;
import com.lojadomecanico.desafio.infra.database.entity.RetrievePasswordEntity;
import com.lojadomecanico.desafio.infra.database.repository.RetrievePasswordRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RetrievePasswordRepositoryImpl implements RetrievePasswordRepository {
    private final RetrievePasswordRepositoryJpa repositoryJpa;

    @Override
    public RetrievePassword findCode(String code) {
        return repositoryJpa.findByCode(code)
                .map(RetrievePasswordEntity::fromDomain)
                .orElse(null);

    }

    @Override
    public RetrievePassword findByUserId(Integer userId) {
        return repositoryJpa.findByUserId(userId)
                .map(RetrievePasswordEntity::fromDomain)
                .orElse(null);
    }

    @Override
    public RetrievePassword save(RetrievePassword retrievePassword) {
        return RetrievePasswordEntity.fromDomain(
                repositoryJpa.save(RetrievePasswordEntity
                        .fromEntity(retrievePassword)));
    }

    @Override
    public List<RetrievePassword> listAll() {
        return repositoryJpa.findAll().stream()
                .map(RetrievePasswordEntity::fromDomain)
                .collect(Collectors.toList());
    }

    @Override
    public RetrievePassword listById(Integer id) {
        return repositoryJpa.findById(id)
                .map(RetrievePasswordEntity::fromDomain)
                .orElse(null);
    }

    @Override
    public void deleteById(Integer id) {

    }
}
