package com.lojadomecanico.desafio.domain.repositories;

import com.lojadomecanico.desafio.domain.entities.User;

public interface UserRepository extends RepositoryGeneric<User> {

    User findByEmail(String email);
}
