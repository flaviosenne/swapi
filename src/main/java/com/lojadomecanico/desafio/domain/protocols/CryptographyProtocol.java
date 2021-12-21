package com.lojadomecanico.desafio.domain.protocols;

public interface CryptographyProtocol {

    boolean passwordMatchers(String hash, String password);

    String encode(String password);

}
