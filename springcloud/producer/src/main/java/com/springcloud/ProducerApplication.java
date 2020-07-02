package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableCircuitBreaker //
@EnableEurekaClient
@SpringBootApplication
public class ProducerApplication {
	
	@RequestMapping("/test")
	public String test() {
		return "producer is ok";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}
}