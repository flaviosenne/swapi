package com.lojadomecanico.desafio.infra.config;

import com.lojadomecanico.desafio.domain.dtos.eng.FilmsEngDto;
import com.lojadomecanico.desafio.domain.dtos.eng.PeopleEngDto;
import com.lojadomecanico.desafio.domain.dtos.eng.PlanetsEngDto;
import com.lojadomecanico.desafio.domain.protocols.*;
import com.lojadomecanico.desafio.domain.repositories.RetrievePasswordRepository;
import com.lojadomecanico.desafio.domain.repositories.UserRepository;
import com.lojadomecanico.desafio.domain.usecases.FilmService;
import com.lojadomecanico.desafio.domain.usecases.PeopleService;
import com.lojadomecanico.desafio.domain.usecases.PlanetService;
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

    @Bean
    RequestGenericApiProtocol<FilmsEngDto> films(){
        return new FilmService();
    }

    @Bean
    RequestGenericApiProtocol<PeopleEngDto> people(){
        return new PeopleService();
    }

    @Bean
    RequestGenericApiProtocol<PlanetsEngDto> planets(){
        return new PlanetService();
    }
}
