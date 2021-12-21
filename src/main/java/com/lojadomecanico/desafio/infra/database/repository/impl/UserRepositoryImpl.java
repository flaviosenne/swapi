package com.lojadomecanico.desafio.infra.database.repository.impl;

import com.lojadomecanico.desafio.domain.entities.User;
import com.lojadomecanico.desafio.domain.repositories.RepositoryGeneric;
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
    public class UserRepositoryImpl implements RepositoryGeneric<User> {

    private final UserRepositoryJpa userRepositoryJpa;


    @Override
    public User save(User user) {
        UserEntity entity = userRepositoryJpa.save(UserEntity.fromEntity(user));
        return UserEntity.fromDomain(entity);
    }

    @Override
    public List<User> listAll() {
        return userRepositoryJpa.findAll().stream()
                .map(user -> UserEntity.fromDomain(user))
                .collect(Collectors.toList());
    }

    @Override
    public User listById(Integer id) {
        return userRepositoryJpa.findById(id)
                .map(user -> UserEntity.fromDomain(user))
                .orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        Optional<UserEntity> entity = userRepositoryJpa.findById(id);

        entity.ifPresent(user -> user.setIsActive(0));
    }
}
