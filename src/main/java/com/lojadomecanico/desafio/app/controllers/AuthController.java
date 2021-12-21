package com.lojadomecanico.desafio.app.controllers;

import com.lojadomecanico.desafio.domain.dtos.LoginDto;
import com.lojadomecanico.desafio.domain.dtos.ResponseLoginDto;
import com.lojadomecanico.desafio.domain.protocols.UserProtocol;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserProtocol service;


    @CrossOrigin
    @PostMapping(value = "/login")
    public ResponseLoginDto login(@RequestBody LoginDto dto){
        return service.login(dto);
    }
}
