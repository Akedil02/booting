package com.example.booting.data.repository;

import org.springframework.stereotype.Repository;

@Repository
public class DataRepository {
    private String lastValue = null;

    public void saveLastValue(String value) {
        this.lastValue = value;
    }

    public String getLastValue() {
        return lastValue;
    }
}
