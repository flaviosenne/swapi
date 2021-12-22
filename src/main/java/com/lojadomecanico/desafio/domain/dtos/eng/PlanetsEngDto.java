package com.lojadomecanico.desafio.domain.dtos.eng;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lojadomecanico.desafio.domain.dtos.pt.FilmsPtDto;
import com.lojadomecanico.desafio.domain.dtos.pt.PlanetsPtDto;

import java.util.Date;
import java.util.List;

public class PlanetsEngDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("rotation_period")
    private String rotationPeriod;
    @JsonProperty("orbital_period")
    private String orbitalPeriod;
    @JsonProperty("diameter")
    private String diameter;
    @JsonProperty("climate")
    private String climate;
    @JsonProperty("gravity")
    private String gravity;
    @JsonProperty("terrain")
    private String terrain;
    @JsonProperty("surface_water")
    private String surfaceWater;
    @JsonProperty("population")
    private String population;
    @JsonProperty("residents")
    private List<String> residents;
    @JsonProperty("films")
    private List<String> films;
    @JsonProperty("created")
    private Date createdAt;
    @JsonProperty("edited")
    private Date updatedAt;
    @JsonProperty("url")
    private String url;

    public PlanetsEngDto(){

    }

    public static PlanetsPtDto fromPt(PlanetsEngDto planets){
        return new PlanetsPtDto(
                planets.getName(),
                planets.getRotationPeriod(),
                planets.getOrbitalPeriod(),
                planets.getDiameter(),
                planets.getClimate(),
                planets.getGravity(),
                planets.getTerrain(),
                planets.getSurfaceWater(),
                planets.getPopulation(),
                planets.getResidents(),
                planets.getFilms(),
                planets.getCreatedAt(),
                planets.getUpdatedAt(),
                planets.getUrl()
        );
    }

    public String getName() {
        return name;
    }

    public String getRotationPeriod() {
        return rotationPeriod;
    }

    public String getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public String getDiameter() {
        return diameter;
    }

    public String getClimate() {
        return climate;
    }

    public String getGravity() {
        return gravity;
    }

    public String getTerrain() {
        return terrain;
    }

    public String getSurfaceWater() {
        return surfaceWater;
    }

    public String getPopulation() {
        return population;
    }

    public List<String> getResidents() {
        return residents;
    }

    public List<String> getFilms() {
        return films;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getUrl() {
        return url;
    }



}
