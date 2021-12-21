package com.lojadomecanico.desafio.domain.repositories;

import com.lojadomecanico.desafio.domain.entities.RetrievePassword;

public interface RetrievePasswordRepository extends RepositoryGeneric<RetrievePassword> {

    RetrievePassword findCode(String code);

    RetrievePassword findByUserId(Integer userId);
}
