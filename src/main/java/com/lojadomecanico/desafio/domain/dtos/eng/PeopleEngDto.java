package com.lojadomecanico.desafio.domain.dtos.eng;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lojadomecanico.desafio.domain.dtos.pt.PeoplePtDto;


import java.util.Date;
import java.util.List;

public class PeopleEngDto {
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "height")
    private String height;
    @JsonProperty(value = "mass")
    private String mass;
    @JsonProperty(value = "hair_color")
    private String hairColor;
    @JsonProperty(value = "skin_color")
    private String skinColor;
    @JsonProperty(value = "eye_color")
    private String eyeColor;
    @JsonProperty(value = "birth_year")
    private String birthYear;
    @JsonProperty(value = "gender")
    private String gender;
    @JsonProperty(value = "homeworld")
    private String homeWorld;
    @JsonProperty(value = "films")
    private List<String> films;
    @JsonProperty(value = "species")
    private List<String> species;
    @JsonProperty(value = "vehicles")
    private List<String> vehicles;
    @JsonProperty(value = "starships")
    private List<String> startShips;
    @JsonProperty(value = "created")
    private Date createdAt;
    @JsonProperty(value = "edited")
    private Date updatedAt;
    @JsonProperty(value = "url")
    private String url;

    public PeopleEngDto(){

    }
    public static PeoplePtDto fromPt(PeopleEngDto people){
        return new PeoplePtDto(
                people.getName(),
                people.getHeight(),
                people.getMass(),
                people.getHairColor(),
                people.getSkinColor(),
                people.getEyeColor(),
                people.getBirthYear(),
                people.getGender(),
                people.getHomeWorld(),
                people.getFilms(),
                people.getSpecies(),
                people.getVehicles(),
                people.getStartShips(),
                people.getCreatedAt(),
                people.getUpdatedAt(),
                people.getUrl()
        );
    }

    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getMass() {
        return mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getGender() {
        return gender;
    }

    public String getHomeWorld() {
        return homeWorld;
    }

    public List<String> getFilms() {
        return films;
    }

    public List<String> getSpecies() {
        return species;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public List<String> getStartShips() {
        return startShips;
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
