package com.lojadomecanico.desafio.app.dtos.eng;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class FilmsEngDto {
    @JsonProperty(value = "title")
    private String title;
    @JsonProperty(value = "episode_id")
    private Integer episodeId;
    @JsonProperty(value = "opening_crawl")
    private String openingCrawl;
    @JsonProperty(value = "director")
    private String director;
    @JsonProperty(value = "producer")
    private String producer;
    @JsonProperty(value = "release_date")
    private String releaseDate;
    @JsonProperty(value = "characters")
    private List<String> characters;
    @JsonProperty(value = "planets")
    private List<String> planets;
    @JsonProperty(value = "starships")
    private List<String> startShips;
    @JsonProperty(value = "vehicles")
    private List<String> vehicles;
    @JsonProperty(value = "species")
    private List<String> species;
    @JsonProperty(value = "created")
    private Date createdAt;
    @JsonProperty(value = "edited")
    private Date updatedAt;
    @JsonProperty(value = "url")
    private String url;
}
