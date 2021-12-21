package com.lojadomecanico.desafio.domain.repositories;

import java.util.List;

public interface RepositoryGeneric<ENTITY> {

    ENTITY save(ENTITY entity);

    List<ENTITY> listAll();

    ENTITY listById(Integer id);

    void deleteById(Integer id);
}
