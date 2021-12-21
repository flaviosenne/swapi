package com.lojadomecanico.desafio.domain.protocols;

import com.lojadomecanico.desafio.domain.dtos.LoginDto;
import com.lojadomecanico.desafio.domain.dtos.RedefinePasswordDto;
import com.lojadomecanico.desafio.domain.dtos.ResponseLoginDto;
import com.lojadomecanico.desafio.domain.entities.User;

import java.util.List;

public interface UserProtocol {
    User save(User user);

    ResponseLoginDto login(LoginDto dto);

    List<User> listAll();

    User listById(Integer id);

    User update(User user);

    void deleteById(Integer id);

    void retrievePassword(String email);

    void redefinePassword(RedefinePasswordDto dto);


}
