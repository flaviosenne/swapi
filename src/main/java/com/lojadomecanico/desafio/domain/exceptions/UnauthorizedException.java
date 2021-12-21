package com.lojadomecanico.desafio.domain.exceptions;


public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String msg){
        super(msg);
    }
}
