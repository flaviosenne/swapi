package com.lojadomecanico.desafio.domain.exceptions;


public class BadRequestException extends RuntimeException{
    public BadRequestException(String msg){
        super(msg);
    }
}
