package com.lojadomecanico.desafio.app.controllers;


import com.lojadomecanico.desafio.domain.dtos.eng.PeopleEngDto;
import com.lojadomecanico.desafio.domain.dtos.eng.PlanetsEngDto;
import com.lojadomecanico.desafio.domain.protocols.RequestGenericApiProtocol;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/swapi")
public class PlanetsController {

    private final RequestGenericApiProtocol<PlanetsEngDto> planetsEng;


    @CrossOrigin
    @GetMapping(value = "/planets")
    public List<?> getPlanets(@RequestParam(value = "lang", defaultValue = "eng") String lang){
        List<PlanetsEngDto> planets = planetsEng.listAll();

        if("pt".equals(lang)) {
            return planets.stream().map(PlanetsEngDto::fromPt)
                    .collect(Collectors.toList());
        }

        return planets;
    }

    @CrossOrigin
    @GetMapping(value = "/planets/{id}")
    public Object getFilms(@PathVariable("id")Integer id,
                           @RequestParam(value = "lang", defaultValue = "eng") String lang){
        PlanetsEngDto planet = planetsEng.listById(id);

        if("pt".equals(lang)) {
            return PlanetsEngDto.fromPt(planet);
        }

        return planet;
    }

}
