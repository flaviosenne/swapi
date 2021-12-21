package com.lojadomecanico.desafio.domain.protocols;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public interface AuthenticateProtocol {
    String generateToken(Integer idUser, Date today);

    boolean isValidToken(String token);

    String getTokenHeader(HttpServletRequest request);

    Integer getIdUserByToken(String token);
}
