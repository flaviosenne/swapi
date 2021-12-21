package com.lojadomecanico.desafio.domain.protocols;

import com.lojadomecanico.desafio.domain.entities.User;

public interface EmailProtocol {
    String templateRetrievePassword(User user, String code);

    void sendEmail(String content, String subject, String[] to);
}
