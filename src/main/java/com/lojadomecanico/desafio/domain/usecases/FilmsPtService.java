package com.lojadomecanico.desafio.domain.usecases;

import com.lojadomecanico.desafio.domain.dtos.eng.FilmsEngDto;
import com.lojadomecanico.desafio.domain.dtos.pt.FilmsPtDto;
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

public class FilmsPtService implements RequestGenericApiProtocol<FilmsPtDto> {

    @Override
    public List<FilmsPtDto> listAll() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = this.getHeaders();

        HttpEntity<FilmsPtDto> request = new HttpEntity<>(null, headers);

        return  restTemplate.exchange(URI_SWAPI+"/films", HttpMethod.GET, request,
                new ParameterizedTypeReference<List<FilmsPtDto>>(){})
                .getBody();
    }

    @Override
    public FilmsPtDto listById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = this.getHeaders();

        HttpEntity<FilmsPtDto> request = new HttpEntity<>(null, headers);

        return  restTemplate.exchange(URI_SWAPI+"/films/"+id, HttpMethod.GET, request,
                        new ParameterizedTypeReference<FilmsPtDto>(){})
                .getBody();
    }

    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
