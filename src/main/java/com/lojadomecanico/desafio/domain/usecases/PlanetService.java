package com.lojadomecanico.desafio.domain.usecases;

import com.lojadomecanico.desafio.domain.dtos.ResultListSwapi;
import com.lojadomecanico.desafio.domain.dtos.eng.PeopleEngDto;
import com.lojadomecanico.desafio.domain.dtos.eng.PlanetsEngDto;
import com.lojadomecanico.desafio.domain.protocols.RequestGenericApiProtocol;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static com.lojadomecanico.desafio.domain.usecases.utils.Constants.URI_SWAPI;

public class PlanetService implements RequestGenericApiProtocol<PlanetsEngDto> {

    @Override
    public List<PlanetsEngDto> listAll() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = this.getHeaders();

        HttpEntity<PlanetsEngDto> request = new HttpEntity<>(null, headers);

        ResultListSwapi<PlanetsEngDto> result =
                restTemplate.exchange(URI_SWAPI + "/planets", HttpMethod.GET, request,
                        new ParameterizedTypeReference<ResultListSwapi<PlanetsEngDto>>() {
                        }).getBody();

        return  result == null ? Collections.emptyList() : result.getResults();
    }

    @Override
    public PlanetsEngDto listById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = this.getHeaders();

        HttpEntity<PlanetsEngDto> request = new HttpEntity<>(null, headers);

        return  restTemplate.exchange(URI_SWAPI+"/planets/"+id, HttpMethod.GET, request,
                        new ParameterizedTypeReference<PlanetsEngDto>(){})
                .getBody();
    }

    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
