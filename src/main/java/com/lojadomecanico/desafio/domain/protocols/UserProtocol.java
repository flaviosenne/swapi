package com.lojadomecanico.desafio.domain.protocols;

import com.lojadomecanico.desafio.domain.entities.User;

import java.util.List;

public interface UserProtocol {
    User save(User user);

    List<User> listAll();

    User listById(Integer id);

    User update(User user);

    void deleteById(Integer id);
}
