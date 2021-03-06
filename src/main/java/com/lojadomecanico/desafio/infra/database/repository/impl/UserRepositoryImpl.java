package com.lojadomecanico.desafio.infra.database.repository.impl;

import com.lojadomecanico.desafio.domain.entities.User;
import com.lojadomecanico.desafio.domain.repositories.RepositoryGeneric;
import com.lojadomecanico.desafio.domain.repositories.UserRepository;
import com.lojadomecanico.desafio.infra.database.entity.UserEntity;
import com.lojadomecanico.desafio.infra.database.repository.UserRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
    public class UserRepositoryImpl implements UserRepository {

    private final UserRepositoryJpa userRepositoryJpa;


    @Override
    public User save(User user) {
        UserEntity entity = userRepositoryJpa.save(UserEntity.fromEntity(user));
        return UserEntity.fromDomain(entity);
    }

    @Override
    public List<User> listAll() {
        return userRepositoryJpa.findAll().stream()
                .map(UserEntity::fromDomain)
                .collect(Collectors.toList());
    }

    @Override
    public User listById(Integer id) {
        return userRepositoryJpa.findById(id)
                .map(UserEntity::fromDomain)
                .orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        Optional<UserEntity> entity = userRepositoryJpa.findById(id);

        entity.ifPresent(user -> user.setIsActive(0));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepositoryJpa.findByEmail(email)
                .map(UserEntity::fromDomain);
    }
}
