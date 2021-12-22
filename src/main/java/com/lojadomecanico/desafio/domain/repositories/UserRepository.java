package com.lojadomecanico.desafio.domain.repositories;

import com.lojadomecanico.desafio.domain.entities.User;

import java.util.Optional;

public interface UserRepository extends RepositoryGeneric<User> {

    Optional<User> findByEmail(String email);
}
