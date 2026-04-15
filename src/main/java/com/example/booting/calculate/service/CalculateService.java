package com.example.booting.calculate.service;

import com.example.booting.calculate.dto.CalculateRequest;
import com.example.booting.calculate.dto.CalculateResponse;
import org.springframework.stereotype.Service;
@Service
public class CalculateService {


    public CalculateResponse add(CalculateRequest request){
        int result = request.getA()+ request.getB();
        return new CalculateResponse(result);
    }
}
