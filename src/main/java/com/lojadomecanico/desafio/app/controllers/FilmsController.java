package com.lojadomecanico.desafio.app.controllers;


import com.lojadomecanico.desafio.domain.dtos.eng.FilmsEngDto;
import com.lojadomecanico.desafio.domain.protocols.RequestGenericApiProtocol;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/swapi")
public class FilmsController {

    private final RequestGenericApiProtocol<FilmsEngDto> filmsEng;


    @CrossOrigin
    @GetMapping(value = "/films")
    public List<?> getFilms(@RequestParam(value = "lang", defaultValue = "eng") String lang){
        List<FilmsEngDto> films = filmsEng.listAll();

        if("pt".equals(lang)) {
            return films.stream().map(FilmsEngDto::fromPt)
                    .collect(Collectors.toList());
        }

        return films;
    }

    @CrossOrigin
    @GetMapping(value = "/films/{id}")
    public Object getFilms(@PathVariable("id")Integer id,
                           @RequestParam(value = "lang", defaultValue = "eng") String lang){
        FilmsEngDto film = filmsEng.listById(id);

        if("pt".equals(lang)) {
            return FilmsEngDto.fromPt(film);
        }

        return film;
    }

}
