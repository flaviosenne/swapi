package com.lojadomecanico.desafio.app.controllers;

import com.lojadomecanico.desafio.app.dtos.LoginDto;
import com.lojadomecanico.desafio.app.dtos.ResponseLoginDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @CrossOrigin
    @PostMapping(value = "/login")
    public ResponseLoginDto login(@RequestBody LoginDto dto){
        return null;
    }
}
