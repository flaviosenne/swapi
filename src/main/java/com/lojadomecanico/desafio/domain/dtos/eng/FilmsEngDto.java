package com.lojadomecanico.desafio.domain.dtos.eng;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lojadomecanico.desafio.domain.dtos.pt.FilmsPtDto;

import java.util.Date;
import java.util.List;

public class FilmsEngDto {
    @JsonProperty("title")
    private String title;
    @JsonProperty("episode_id")
    private Integer episodeId;
    @JsonProperty("opening_crawl")
    private String openingCrawl;
    @JsonProperty("director")
    private String director;
    @JsonProperty("producer")
    private String producer;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("characters")
    private List<String> characters;
    @JsonProperty("planets")
    private List<String> planets;
    @JsonProperty("starships")
    private List<String> startShips;
    @JsonProperty("vehicles")
    private List<String> vehicles;
    @JsonProperty("species")
    private List<String> species;
    @JsonProperty("created")
    private Date createdAt;
    @JsonProperty("edited")
    private Date updatedAt;
    @JsonProperty("url")
    private String url;

    public FilmsEngDto(){

    }

    public static FilmsPtDto fromPt(FilmsEngDto films){
        return new FilmsPtDto(
                films.getTitle(),
                films.getEpisodeId(),
                films.getOpeningCrawl(),
                films.getDirector(),
                films.getProducer(),
                films.getReleaseDate(),
                films.getCharacters(),
                films.getPlanets(),
                films.getStartShips(),
                films.getVehicles(),
                films.getSpecies(),
                films.getCreatedAt(),
                films.getUpdatedAt(),
                films.getUrl()
        );
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
