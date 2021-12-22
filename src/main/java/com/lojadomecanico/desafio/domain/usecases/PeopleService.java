package com.lojadomecanico.desafio.domain.usecases;

import com.lojadomecanico.desafio.domain.dtos.ResultListSwapi;
import com.lojadomecanico.desafio.domain.dtos.eng.FilmsEngDto;
import com.lojadomecanico.desafio.domain.dtos.eng.PeopleEngDto;
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

public class PeopleService implements RequestGenericApiProtocol<PeopleEngDto> {

    @Override
    public List<PeopleEngDto> listAll() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = this.getHeaders();

        HttpEntity<PeopleEngDto> request = new HttpEntity<>(null, headers);

        ResultListSwapi<PeopleEngDto> result =
                restTemplate.exchange(URI_SWAPI + "/people", HttpMethod.GET, request,
                        new ParameterizedTypeReference<ResultListSwapi<PeopleEngDto>>() {
                        }).getBody();

        return  result == null ? Collections.emptyList() : result.getResults();
    }

    @Override
    public PeopleEngDto listById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = this.getHeaders();

        HttpEntity<PeopleEngDto> request = new HttpEntity<>(null, headers);

        return  restTemplate.exchange(URI_SWAPI+"/people/"+id, HttpMethod.GET, request,
                        new ParameterizedTypeReference<PeopleEngDto>(){})
                .getBody();
    }

    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
