package com.lojadomecanico.desafio.app.dtos.eng;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
public class PeopleEngDto {
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "height")
    private String height;
    @JsonProperty(value = "miss")
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
}
