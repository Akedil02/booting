package com.example.booting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BootingApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootingApplication.class, args);
	}

}
