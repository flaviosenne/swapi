package com.lojadomecanico.desafio.app.controllers;


import com.lojadomecanico.desafio.domain.dtos.eng.FilmsEngDto;
import com.lojadomecanico.desafio.domain.dtos.pt.FilmsPtDto;
import com.lojadomecanico.desafio.domain.protocols.RequestGenericApiProtocol;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/swapi")
public class SwapiController {

    private final RequestGenericApiProtocol<FilmsPtDto> filmsPt;
    private final RequestGenericApiProtocol<FilmsEngDto> filmsEng;


    @CrossOrigin
    @GetMapping(value = "/films")
    public List<?> getFilms(@RequestParam(value = "lang", defaultValue = "eng") String lang){
        return "pt".equals(lang) ? filmsPt.listAll() : filmsEng.listAll();
    }

    @CrossOrigin
    @GetMapping(value = "/films/{id}")
    public Object getFilms(@PathVariable("id")Integer id,
                           @RequestParam(value = "lang", defaultValue = "eng") String lang){
        return "pt".equals(lang) ? filmsPt.listById(id) : filmsEng.listById(id);
    }

}
