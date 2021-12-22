package com.lojadomecanico.desafio.domain.dtos.pt;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

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

    public FilmsPtDto(String title, Integer episodeId, String openingCrawl, String director, String producer, String releaseDate, List<String> characters, List<String> planets, List<String> startShips, List<String> vehicles, List<String> species, Date createdAt, Date updatedAt, String url) {
        this.title = title;
        this.episodeId = episodeId;
        this.openingCrawl = openingCrawl;
        this.director = director;
        this.producer = producer;
        this.releaseDate = releaseDate;
        this.characters = characters;
        this.planets = planets;
        this.startShips = startShips;
        this.vehicles = vehicles;
        this.species = species;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.url = url;
    }

    public FilmsPtDto(){

    }

    public String getTitle() {
        return title;
    }

    public Integer getEpisodeId() {
        return episodeId;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public String getDirector() {
        return director;
    }

    public String getProducer() {
        return producer;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public List<String> getPlanets() {
        return planets;
    }

    public List<String> getStartShips() {
        return startShips;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public List<String> getSpecies() {
        return species;
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
