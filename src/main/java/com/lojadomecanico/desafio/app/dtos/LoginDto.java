package com.lojadomecanico.desafio.app.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginDto {
    private final String email;
    private final String password;
}
