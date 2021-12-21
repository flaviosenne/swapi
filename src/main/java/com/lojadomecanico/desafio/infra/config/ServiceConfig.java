package com.lojadomecanico.desafio.infra.config;

import com.lojadomecanico.desafio.domain.protocols.AuthenticateProtocol;
import com.lojadomecanico.desafio.domain.protocols.CryptographyProtocol;
import com.lojadomecanico.desafio.domain.protocols.EmailProtocol;
import com.lojadomecanico.desafio.domain.protocols.UserProtocol;
import com.lojadomecanico.desafio.domain.repositories.RetrievePasswordRepository;
import com.lojadomecanico.desafio.domain.repositories.UserRepository;
import com.lojadomecanico.desafio.domain.usecases.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    UserProtocol userProtocol(UserRepository userRepositoryGeneric,
                              CryptographyProtocol cryptographyProtocol,
                              AuthenticateProtocol authenticateProtocol,
                              RetrievePasswordRepository retrievePasswordRepository,
                              EmailProtocol emailProtocol){
        return new UserServiceImpl(
                userRepositoryGeneric,
                cryptographyProtocol,
                authenticateProtocol,
                retrievePasswordRepository,
                emailProtocol);
    }
}
