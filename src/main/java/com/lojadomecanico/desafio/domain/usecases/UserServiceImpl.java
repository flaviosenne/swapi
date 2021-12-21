package com.lojadomecanico.desafio.domain.usecases;

import com.lojadomecanico.desafio.domain.dtos.LoginDto;
import com.lojadomecanico.desafio.domain.dtos.RedefinePasswordDto;
import com.lojadomecanico.desafio.domain.dtos.ResponseLoginDto;
import com.lojadomecanico.desafio.domain.entities.RetrievePassword;
import com.lojadomecanico.desafio.domain.entities.User;
import com.lojadomecanico.desafio.domain.exceptions.BadRequestException;
import com.lojadomecanico.desafio.domain.exceptions.NotFoundException;
import com.lojadomecanico.desafio.domain.protocols.AuthenticateProtocol;
import com.lojadomecanico.desafio.domain.protocols.CryptographyProtocol;
import com.lojadomecanico.desafio.domain.protocols.EmailProtocol;
import com.lojadomecanico.desafio.domain.protocols.UserProtocol;
import com.lojadomecanico.desafio.domain.repositories.RetrievePasswordRepository;
import com.lojadomecanico.desafio.domain.repositories.UserRepository;
import com.lojadomecanico.desafio.domain.usecases.utils.GenericService;


import java.util.Date;
import java.util.List;

import static com.lojadomecanico.desafio.domain.messages.email.MessagesEmail.RETRIEVE_PASSWORD_SUBJECT;
import static com.lojadomecanico.desafio.domain.messages.exception.UserMessages.*;

public class UserServiceImpl implements UserProtocol {
    private final UserRepository repository;
    private final CryptographyProtocol cryptographyProtocol;
    private final AuthenticateProtocol authenticateProtocol;
    private final RetrievePasswordRepository retrievePasswordRepository;
    private final EmailProtocol emailProtocol;

    public UserServiceImpl(UserRepository repository,
                           CryptographyProtocol cryptographyProtocol,
                           AuthenticateProtocol authenticateProtocol,
                           RetrievePasswordRepository retrievePasswordRepository,
                           EmailProtocol emailProtocol){
        this.repository = repository;
        this.cryptographyProtocol = cryptographyProtocol;
        this.authenticateProtocol = authenticateProtocol;
        this.retrievePasswordRepository = retrievePasswordRepository;
        this.emailProtocol = emailProtocol;
    }

    @Override
    public User save(User user) {
        GenericService.validateField(user.getEmail(), EMAIL_NOT_PROVIDER);
        GenericService.validateField(user.getPassword(), PASSWORD_NOT_PROVIDER);

        String password = GenericService.convertBase64ToString(user.getPassword());

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

        GenericService.validateField(dto.getEmail(), EMAIL_NOT_PROVIDER);

        GenericService.validateField(dto.getPassword(), PASSWORD_NOT_PROVIDER);

        String password = GenericService.convertBase64ToString(dto.getPassword());

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

    @Override
    public void retrievePassword(String email) {
        User user = repository.findByEmail(email);

        if(user == null) throw new NotFoundException(USER_NOT_FOUND);

        String code = GenericService.generateRandomCode();

        RetrievePassword oldCode = retrievePasswordRepository.findByUserId(user.getId());
        
        if(oldCode != null){
            retrievePasswordRepository.save(RetrievePassword
                    .cloneRetrievePasswordDelete(oldCode));
        }

        RetrievePassword codeSaved = retrievePasswordRepository.save(
                new RetrievePassword(null, code, 1, new Date(), user)
        );

        String content = emailProtocol.templateRetrievePassword(user, codeSaved.getCode());

        emailProtocol.sendEmail(content, RETRIEVE_PASSWORD_SUBJECT, new String[]{user.getEmail()});

    }

    @Override
    public void redefinePassword(RedefinePasswordDto dto) {
        RetrievePassword code = retrievePasswordRepository.findCode(dto.getCode());

        if(code == null) throw new BadRequestException("Codigo não existente");

        boolean alreadyExpirated = (new Date().getTime() - code.getCreatedAt().getTime() > code.getExpiration());

        if(alreadyExpirated) throw new BadRequestException("Codigo já expirou");

        User user = repository.listById(code.getUser().getId());

        if(user == null) throw new BadRequestException("Codigo não vinculado ao usuário");

        String password = GenericService.convertBase64ToString(dto.getPassword());

        String hash = cryptographyProtocol.encode(password);

        User newUser = User.cloneUserRedefinePassword(user, hash);

        repository.save(newUser);
    }

}
