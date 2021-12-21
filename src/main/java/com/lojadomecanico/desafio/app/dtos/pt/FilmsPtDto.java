package com.lojadomecanico.desafio.app.dtos.pt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class FilmsPtDto {
    @JsonProperty(value = "titulo")
    private String title;
    @JsonProperty(value = "episodio_id")
    private Integer episodeId;
    @JsonProperty(value = "rastejo_abertura")
    private String openingCrawl;
    @JsonProperty(value = "diretor")
    private String director;
    @JsonProperty(value = "produtores")
    private String producer;
    @JsonProperty(value = "date_lancamento")
    private String releaseDate;
    @JsonProperty(value = "personagens")
    private List<String> characters;
    @JsonProperty(value = "planetas")
    private List<String> planets;
    @JsonProperty(value = "naves_estelares")
    private List<String> startShips;
    @JsonProperty(value = "veiculos")
    private List<String> vehicles;
    @JsonProperty(value = "especies")
    private List<String> species;
    @JsonProperty(value = "criacao")
    private Date createdAt;
    @JsonProperty(value = "edicao")
    private Date updatedAt;
    @JsonProperty(value = "url")
    private String url;
}
