package com.example.booting.data.service;

import com.example.booting.data.dto.Joke;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ExternalApiClient {

    private final RestTemplate restTemplate;

    public ExternalApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchJoke() {
        String url = "https://api.chucknorris.io/jokes/random";
        Joke joke = restTemplate.getForObject(url, Joke.class);

        if(joke == null || joke.getValue() == null) {
            return "No joke available";
        }

        return joke.getValue();
    }
}
