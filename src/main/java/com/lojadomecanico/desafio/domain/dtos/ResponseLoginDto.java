package com.lojadomecanico.desafio.domain.dtos;

public class ResponseLoginDto {
    private final String token;

    public ResponseLoginDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
