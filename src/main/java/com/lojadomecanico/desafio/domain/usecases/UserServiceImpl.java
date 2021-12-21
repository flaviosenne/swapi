package com.lojadomecanico.desafio.domain.usecases;

import com.lojadomecanico.desafio.domain.entities.User;
import com.lojadomecanico.desafio.domain.exceptions.NotFoundException;
import com.lojadomecanico.desafio.domain.protocols.UserProtocol;
import com.lojadomecanico.desafio.domain.repositories.RepositoryGeneric;


import java.util.List;

import static com.lojadomecanico.desafio.domain.messages.exception.UserMessages.USER_NOT_FOUND;

public class UserServiceImpl implements UserProtocol {
    private final RepositoryGeneric<User> repository;

    public UserServiceImpl(RepositoryGeneric<User> repository){
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public List<User> listAll() {
        return repository.listAll();
    }

    @Override
    public User listById(Integer id) {
        return repository.listById(id);
    }

    @Override
    public User update(User user) {
        User existUser = repository.listById(user.getId());

        if(existUser == null) throw new NotFoundException(USER_NOT_FOUND);

        User newUser = User.cloneUserUpdate(user, existUser);

        return repository.save(newUser);
    }

    @Override
    public void deleteById(Integer id) {
        User existUser = repository.listById(id);

        if(existUser == null) throw new NotFoundException(USER_NOT_FOUND);

        User user = User.cloneUserDelete(existUser);

        repository.save(user);
    }
}
