package com.lojadomecanico.desafio.app.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseLoginDto {
    private final String token;
}
