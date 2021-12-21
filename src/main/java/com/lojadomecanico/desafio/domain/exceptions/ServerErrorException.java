package com.lojadomecanico.desafio.domain.exceptions;

public class ServerErrorException extends RuntimeException{
    public ServerErrorException(String msg){
        super(msg);
    }
}
