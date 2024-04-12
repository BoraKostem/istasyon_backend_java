package com.istasyon.backend.services;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Service
public class RecommendationService {
    public Long[] getRecommendations(Long id, Integer page, Integer offset) {
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl("http://recommendations:5000/employee/" + id)
                .queryParam("page", page)
                .queryParam("offset", offset)
                .encode()
                .toUriString();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<Long[]> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Long[].class);

        return responseEntity.getBody();
    }
}