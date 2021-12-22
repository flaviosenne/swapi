package com.lojadomecanico.desafio.repository;

import com.lojadomecanico.desafio.infra.database.entity.RetrievePasswordEntity;
import com.lojadomecanico.desafio.infra.database.entity.UserEntity;
import com.lojadomecanico.desafio.infra.database.repository.RetrievePasswordRepositoryJpa;
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
@DisplayName("Retrieve password repository test")
class RetrievePasswordRepositoryIntegrationTest {
    final RetrievePasswordRepositoryJpa repository;
    final UserRepositoryJpa userRepositoryJpa;

    @Test
    @DisplayName("Should get code by user id")
    void getCodeByUser(){

        // given
        UserEntity user = userRepositoryJpa
                .save(UserEntity.builder()
                        .name("name")
                .build());


        RetrievePasswordEntity code = repository
                .save(RetrievePasswordEntity.builder()
                        .code("random_code")
                        .isValid(1)
                        .user(user)
                .build());

        // when
        Optional<RetrievePasswordEntity> codeDB = repository.findByUserId(user.getId());


        // then
        BDDAssertions.assertThat(codeDB).isPresent();
        BDDAssertions.assertThat(codeDB.get().getCode()).isEqualTo(code.getCode());

    }
}
