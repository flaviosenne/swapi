package com.lojadomecanico.desafio.app.controllers;

import com.lojadomecanico.desafio.app.dtos.UserDto;
import com.lojadomecanico.desafio.domain.entities.User;
import com.lojadomecanico.desafio.domain.protocols.UserProtocol;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserProtocol service;

    @CrossOrigin
    @GetMapping
    public List<UserDto> listUsers(){
        List<User> users = service.listAll();

        return UserDto.fromDtoList(users);
    }

    @CrossOrigin
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserDto save(@RequestBody UserDto dto){
        User userToSave = UserDto.fromDomain(dto);
        User userSaved = service.save(userToSave);
        return UserDto.fromDto(userSaved);
    }
}
