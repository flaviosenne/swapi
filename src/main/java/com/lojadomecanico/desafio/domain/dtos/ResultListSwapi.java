package com.lojadomecanico.desafio.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResultListSwapi<DTO> {
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("next")
    private String next;
    @JsonProperty("previous")
    private boolean previous;
    @JsonProperty("results")
    private List<DTO> results;

    public ResultListSwapi(){

    }

    public ResultListSwapi(Integer count, String next, boolean previous, List<DTO> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public String isNext() {
        return next;
    }

    public boolean isPrevious() {
        return previous;
    }

    public List<DTO> getResults() {
        return results;
    }
}
