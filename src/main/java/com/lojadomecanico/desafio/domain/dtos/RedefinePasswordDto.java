package com.lojadomecanico.desafio.domain.dtos;


public class RedefinePasswordDto {
    private final String code;
    private final String password;

    public RedefinePasswordDto(String code, String password) {
        this.code = code;
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public String getPassword() {
        return password;
    }

}
