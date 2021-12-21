package com.lojadomecanico.desafio.app.dtos.pt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
public class PeoplePtDto {
    @JsonProperty(value = "nome")
    private String name;
    @JsonProperty(value = "altura")
    private String height;
    @JsonProperty(value = "massa")
    private String mass;
    @JsonProperty(value = "cor_cabelo")
    private String hairColor;
    @JsonProperty(value = "cor_pele")
    private String skinColor;
    @JsonProperty(value = "cor_olhos")
    private String eyeColor;
    @JsonProperty(value = "ano_nascimento")
    private String birthYear;
    @JsonProperty(value = "sexo")
    private String gender;
    @JsonProperty(value = "planeta_natal")
    private String homeWorld;
    @JsonProperty(value = "filmes")
    private List<String> films;
    @JsonProperty(value = "especies")
    private List<String> species;
    @JsonProperty(value = "veiculos")
    private List<String> vehicles;
    @JsonProperty(value = "naves_estelares")
    private List<String> startShips;
    @JsonProperty(value = "criacao")
    private Date createdAt;
    @JsonProperty(value = "edicao")
    private Date updatedAt;
    @JsonProperty(value = "url")
    private String url;
}
