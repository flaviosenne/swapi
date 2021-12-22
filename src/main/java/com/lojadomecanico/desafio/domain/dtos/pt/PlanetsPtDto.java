package com.lojadomecanico.desafio.domain.dtos.pt;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class PlanetsPtDto {
    @JsonProperty("nome")
    private String name;
    @JsonProperty("periodo_rotacao")
    private String rotationPeriod;
    @JsonProperty("periodo_orbita")
    private String orbitalPeriod;
    @JsonProperty("diametro")
    private String diameter;
    @JsonProperty("clima")
    private String climate;
    @JsonProperty("gravidade")
    private String gravity;
    @JsonProperty("terreno")
    private String terrain;
    @JsonProperty("agua_superficie")
    private String surfaceWater;
    @JsonProperty("populacao")
    private String population;
    @JsonProperty("moradores")
    private List<String> residents;
    @JsonProperty("filmes")
    private List<String> films;
    @JsonProperty("criacao")
    private Date createdAt;
    @JsonProperty("edicao")
    private Date updatedAt;
    @JsonProperty("url")
    private String url;

    public PlanetsPtDto(String name, String rotationPeriod, String orbitalPeriod, String diameter, String climate, String gravity, String terrain, String surfaceWater, String population, List<String> residents, List<String> films, Date createdAt, Date updatedAt, String url) {
        this.name = name;
        this.rotationPeriod = rotationPeriod;
        this.orbitalPeriod = orbitalPeriod;
        this.diameter = diameter;
        this.climate = climate;
        this.gravity = gravity;
        this.terrain = terrain;
        this.surfaceWater = surfaceWater;
        this.population = population;
        this.residents = residents;
        this.films = films;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.url = url;
    }

    public PlanetsPtDto(){

    }

}
