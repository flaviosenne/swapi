package com.lojadomecanico.desafio.infra.adapters;

import com.lojadomecanico.desafio.domain.exceptions.ServerErrorException;
import com.lojadomecanico.desafio.domain.protocols.CryptographyProtocol;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CryptographyBcryptImpl implements CryptographyProtocol {

    @Override
    public boolean passwordMatchers(String hash, String password) {
        try {
            return  new BCryptPasswordEncoder().matches(password, hash);
        }catch (Exception e){
            throw new ServerErrorException(e.getMessage());
        }
    }

    @Override
    public String encode(String password){
        try{
            return new BCryptPasswordEncoder().encode(password);
        }catch (Exception e){
            throw new ServerErrorException(e.getMessage());
        }
    }

}
