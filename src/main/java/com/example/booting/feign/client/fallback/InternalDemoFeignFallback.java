package com.example.booting.feign.client.fallback;

import com.example.booting.feign.client.InternalDemoFeignClient;
import org.springframework.stereotype.Component;

@Component
public class InternalDemoFeignFallback implements InternalDemoFeignClient {

    @Override
    public String callHello() {
        return "Fallback response from Feign";
    }
}
