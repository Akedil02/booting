package com.example.booting.feign.client;

import com.example.booting.feign.client.fallback.InternalDemoFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "internalDemoFeignClient",
        url = "${demo.feign.base-url:http://localhost:8080}",
        fallback = InternalDemoFeignFallback.class
)
public interface InternalDemoFeignClient {

    @GetMapping("/hello")
    String callHello();
}
