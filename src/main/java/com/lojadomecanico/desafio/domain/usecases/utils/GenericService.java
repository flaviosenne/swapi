package com.lojadomecanico.desafio.domain.usecases.utils;

import com.lojadomecanico.desafio.domain.exceptions.BadRequestException;
import com.lojadomecanico.desafio.domain.exceptions.ServerErrorException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

public class GenericService {
    public static void validateField(Object obj, String msg){
        if(obj == null) throw new BadRequestException(msg);
    }

    public static String convertBase64ToString(String base64){
        try{
            return new String(Base64.getDecoder().decode(base64));
        }catch (Exception e){
            throw new ServerErrorException(e.getMessage());
        }
    }

    public static String generateRandomCode(){
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }
}
