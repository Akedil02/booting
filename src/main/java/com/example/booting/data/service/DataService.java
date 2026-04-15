package com.example.booting.data.service;

import com.example.booting.data.dto.DataResponse;
import com.example.booting.data.repository.DataRepository;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    private final ExternalApiClient externalApiClient;
    private final DataRepository dataRepository;

    public DataService(ExternalApiClient externalApiClient, DataRepository dataRepository) {
        this.externalApiClient = externalApiClient;
        this.dataRepository = dataRepository;
    }

    public DataResponse getData() {
        String value = externalApiClient.fetchJoke();
        dataRepository.saveLastValue(value);
        return new DataResponse("external-api", value);
    }
}
