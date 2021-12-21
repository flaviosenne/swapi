package com.lojadomecanico.desafio.domain.usecases;

import com.lojadomecanico.desafio.domain.dtos.LoginDto;
import com.lojadomecanico.desafio.domain.dtos.ResponseLoginDto;
import com.lojadomecanico.desafio.domain.entities.User;
import com.lojadomecanico.desafio.domain.exceptions.BadRequestException;
import com.lojadomecanico.desafio.domain.exceptions.NotFoundException;
import com.lojadomecanico.desafio.domain.protocols.AuthenticateProtocol;
import com.lojadomecanico.desafio.domain.protocols.CryptographyProtocol;
import com.lojadomecanico.desafio.domain.protocols.UserProtocol;
import com.lojadomecanico.desafio.domain.repositories.UserRepository;
import com.lojadomecanico.desafio.domain.usecases.utils.ValidateFields;


import java.util.Date;
import java.util.List;

import static com.lojadomecanico.desafio.domain.messages.exception.UserMessages.*;

public class UserServiceImpl implements UserProtocol {
    private final UserRepository repository;
    private final CryptographyProtocol cryptographyProtocol;
    private final AuthenticateProtocol authenticateProtocol;

    public UserServiceImpl(UserRepository repository,
                           CryptographyProtocol cryptographyProtocol,
                           AuthenticateProtocol authenticateProtocol){
        this.repository = repository;
        this.cryptographyProtocol = cryptographyProtocol;
        this.authenticateProtocol = authenticateProtocol;
    }

    @Override
    public User save(User user) {
        ValidateFields.validateField(user.getEmail(), EMAIL_NOT_PROVIDER);
        ValidateFields.validateField(user.getPassword(), PASSWORD_NOT_PROVIDER);

        String password = ValidateFields.convertBase64ToString(user.getPassword());

        String hash = cryptographyProtocol.encode(password);

        User userToSave = new User(
                null,
                user.getName(),
                user.getEmail(),
                hash,
                user.getCpfCnpj(),
                user.getIsCnpj(),
                1,
                new Date(),
                null
                );

        return repository.save(userToSave);

    }

    @Override
    public ResponseLoginDto login(LoginDto dto) {

        ValidateFields.validateField(dto.getEmail(), EMAIL_NOT_PROVIDER);

        ValidateFields.validateField(dto.getPassword(), PASSWORD_NOT_PROVIDER);

        String password = ValidateFields.convertBase64ToString(dto.getPassword());

        User user = repository.findByEmail(dto.getEmail());

        if(user == null) throw new BadRequestException(INCORRECT_CREDENTIALS);

        boolean isMatchers = cryptographyProtocol.passwordMatchers(user.getPassword(), password);

        if(!isMatchers) throw new BadRequestException(INCORRECT_CREDENTIALS);

        String token = authenticateProtocol.generateToken(user.getId(), new Date());

        return new ResponseLoginDto(token);
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
