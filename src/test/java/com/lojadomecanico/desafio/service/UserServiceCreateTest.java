package com.lojadomecanico.desafio.service;

import com.lojadomecanico.desafio.domain.entities.User;
import com.lojadomecanico.desafio.domain.exceptions.BadRequestException;
import com.lojadomecanico.desafio.domain.exceptions.ServerErrorException;
import com.lojadomecanico.desafio.domain.protocols.AuthenticateProtocol;
import com.lojadomecanico.desafio.domain.protocols.CryptographyProtocol;
import com.lojadomecanico.desafio.domain.protocols.EmailProtocol;
import com.lojadomecanico.desafio.domain.protocols.UserProtocol;
import com.lojadomecanico.desafio.domain.repositories.RetrievePasswordRepository;
import com.lojadomecanico.desafio.domain.repositories.UserRepository;
import com.lojadomecanico.desafio.domain.usecases.UserServiceImpl;
import com.lojadomecanico.desafio.mocks.UserMock;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import static com.lojadomecanico.desafio.domain.messages.exception.UserMessages.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Create user service test")
class UserServiceCreateTest {
    UserProtocol service;

    @Mock
    UserRepository userRepository;
    @Mock
    AuthenticateProtocol auth;
    @Mock
    CryptographyProtocol cryptography;
    @Mock
    RetrievePasswordRepository retrievePasswordRepository;
    @Mock
    EmailProtocol emailProtocol;

    @BeforeEach
    void setup(){
        service = new UserServiceImpl(userRepository,
                cryptography, auth,
                retrievePasswordRepository, emailProtocol);

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(cryptography.encode(anyString())).thenReturn("hash");
        when(userRepository.save(any(User.class))).thenReturn(UserMock.getUserComplete());
    }

    @Test
    @DisplayName("Should throw bad request exception when email is not provider")
    void emailNotProvider(){
        User user = new User(null, "joao", null,
                "pass_base64", "cpf_valid", 0,
                null, null, null);

        Throwable exception = BDDAssertions.catchThrowable(()->service.save(user));

        BDDAssertions.assertThat(exception).isInstanceOf(BadRequestException.class)
                .hasMessage(EMAIL_NOT_PROVIDER);

        verify(cryptography, never()).encode(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    @DisplayName("Should throw bad request exception when password is not provider")
    void passwordNotProvider(){
        User user = new User(null, "joao", "joao@email",
                null, "cpf_valid", 0,
                null, null, null);

        Throwable exception = BDDAssertions.catchThrowable(()->service.save(user));

        BDDAssertions.assertThat(exception).isInstanceOf(BadRequestException.class)
                .hasMessage(PASSWORD_NOT_PROVIDER);

        verify(cryptography, never()).encode(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    @DisplayName("Should throw bad request exception when email already exist in DB")
    void emailAlreadyExist(){
        User user = new User(null, "joao", "exist_email",
                "pass_base64", "cpf_valid", 0,
                null, null, null);

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(UserMock.getUserComplete()));
        Throwable exception = BDDAssertions.catchThrowable(()->service.save(user));

        BDDAssertions.assertThat(exception).isInstanceOf(BadRequestException.class)
                .hasMessage(USER_ALREADY_EXIST_EMAIL);

        verify(cryptography, never()).encode(anyString());
        verify(userRepository, never()).save(any(User.class));
    }


    @Test
    @DisplayName("Should throw bad request exception when base 64 in password is invalid")
    void invalidBase64(){
        User user = new User(null, "joao", "exist_email",
                "pass_base64_invalid",
                "cpf_valid", 0,
                null, null, null);

        Throwable exception = BDDAssertions.catchThrowable(()->service.save(user));

        BDDAssertions.assertThat(exception).isInstanceOf(ServerErrorException.class);

        verify(cryptography, never()).encode(anyString());
        verify(userRepository, never()).save(any(User.class));
    }


    @Test
    @DisplayName("Should save user with password hash in DB")
    void saveUser(){
        User user = new User(null, "joao", "exist_email",
                Base64.getEncoder().encodeToString("pass_base64".getBytes(StandardCharsets.UTF_8)),
                "cpf_valid", 0,
                null, null, null);

        User result = service.save(user);

        BDDAssertions.assertThat(result).isNotNull();
        BDDAssertions.assertThat(result.getPassword()).isNotNull().isEqualTo("hash");

        verify(cryptography, times(1)).encode(anyString());
        verify(userRepository, times(1)).save(any(User.class));
    }
}
