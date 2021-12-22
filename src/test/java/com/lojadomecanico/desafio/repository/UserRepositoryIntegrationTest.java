package com.lojadomecanico.desafio.repository;

import com.lojadomecanico.desafio.infra.database.entity.UserEntity;
import com.lojadomecanico.desafio.infra.database.repository.UserRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@DataJpaTest
@DisplayName("User repository test")
class UserRepositoryIntegrationTest {
    final UserRepositoryJpa repository;

    @BeforeEach
    void setup(){
        UserEntity user = UserEntity.builder()
                .name("name")
                .password("hash")
                .createdAt(new Date())
                .email("valid_email")
                .cpfCnpj("valid_cpf")
                .isCnpj(0)
                .isActive(1)
                .build();

        repository.save(user);

    }

    @Test
    @DisplayName("Should get user by email")
    void getUserByEmail(){

        String email = "valid_email";

        Optional<UserEntity> user = repository.findByEmail(email);

        BDDAssertions.assertThat(user).isPresent();
        BDDAssertions.assertThat(user.get().getId()).isNotNull();
    }
}
