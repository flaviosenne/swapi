package com.lojadomecanico.desafio.infra.config;

import com.lojadomecanico.desafio.domain.entities.User;
import com.lojadomecanico.desafio.domain.protocols.UserProtocol;
import com.lojadomecanico.desafio.domain.repositories.RepositoryGeneric;
import com.lojadomecanico.desafio.domain.usecases.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    UserProtocol userProtocol(RepositoryGeneric<User> userRepositoryGeneric){
        return new UserServiceImpl(userRepositoryGeneric);
    }
}
