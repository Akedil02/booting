package com.example.booting.feign.controller;

import com.example.booting.feign.client.InternalDemoFeignClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign-demo")
@Tag(name = "Feign Demo", description = "Demo endpoints for Feign client calls")
public class FeignDemoController {

    private final InternalDemoFeignClient internalDemoFeignClient;

    public FeignDemoController(InternalDemoFeignClient internalDemoFeignClient) {
        this.internalDemoFeignClient = internalDemoFeignClient;
    }

    @Operation(summary = "Call /hello via Feign client")
    @GetMapping("/call")
    public ResponseEntity<String> call() {
        return ResponseEntity.ok(internalDemoFeignClient.callHello());
    }
}
