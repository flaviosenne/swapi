package com.lojadomecanico.desafio.infra.security;

import com.lojadomecanico.desafio.domain.exceptions.NotFoundException;
import com.lojadomecanico.desafio.infra.database.entity.UserEntity;
import com.lojadomecanico.desafio.infra.database.repository.UserRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.lojadomecanico.desafio.domain.messages.exception.UserMessages.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticateSecurity implements UserDetailsService {

    private final UserRepositoryJpa userRepositoryJpa;

    @Override @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = this.userRepositoryJpa.findByEmail(email)
                .orElseThrow(()-> new NotFoundException(USER_NOT_FOUND));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                null);
    }

}
