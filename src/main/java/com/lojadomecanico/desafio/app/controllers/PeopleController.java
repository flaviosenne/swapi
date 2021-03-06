package com.lojadomecanico.desafio.app.controllers;


import com.lojadomecanico.desafio.domain.dtos.eng.FilmsEngDto;
import com.lojadomecanico.desafio.domain.dtos.eng.PeopleEngDto;
import com.lojadomecanico.desafio.domain.protocols.RequestGenericApiProtocol;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/swapi")
public class PeopleController {

    private final RequestGenericApiProtocol<PeopleEngDto> peopleEng;


    @CrossOrigin
    @GetMapping(value = "/people")
    public List<?> getPeoples(@RequestParam(value = "lang", defaultValue = "eng") String lang){
        List<PeopleEngDto> peoples = peopleEng.listAll();

        if("pt".equals(lang)) {
            return peoples.stream().map(PeopleEngDto::fromPt)
                    .collect(Collectors.toList());
        }

        return peoples;
    }

    @CrossOrigin
    @GetMapping(value = "/people/{id}")
    public Object getPeople(@PathVariable("id")Integer id,
                           @RequestParam(value = "lang", defaultValue = "eng") String lang){
        PeopleEngDto people = peopleEng.listById(id);

        if("pt".equals(lang)) {
            return PeopleEngDto.fromPt(people);
        }

        return people;
    }

}
