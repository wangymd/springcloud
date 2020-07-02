package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class ZuulApplication {
	
	@RequestMapping("/test")
	public String test() {
		return "zuul is ok";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}
}