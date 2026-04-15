package com.example.booting.data.dto;

public class DataResponse {
    private final String source;
    private final String value;

    public DataResponse(String source, String value) {
        this.source = source;
        this.value = value;
    }

    public String getSource() {
        return source;
    }

    public String getValue() {
        return value;
    }
}
