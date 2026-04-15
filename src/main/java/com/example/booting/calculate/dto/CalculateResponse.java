package com.example.booting.calculate.dto;

public class CalculateResponse {

    private int result;

    public CalculateResponse(int result) {
        this.result = result;
    }


    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
