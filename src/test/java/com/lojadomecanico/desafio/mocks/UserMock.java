package com.lojadomecanico.desafio.mocks;

import com.lojadomecanico.desafio.domain.entities.User;

import java.util.Date;

public class UserMock {
    public static User getUserComplete(){
        return new User(1, "joao", "joao@email",
                "hash", "cpf_valid", 0,
                1, new Date(), new Date());
    }
}
