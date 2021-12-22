package com.lojadomecanico.desafio.domain.protocols;


import java.util.List;

public interface RequestGenericApiProtocol<DTO> {
    List<DTO> listAll();

    DTO listById(Integer id);


}
