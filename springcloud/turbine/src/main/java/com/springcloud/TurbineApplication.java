package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableHystrixDashboard //启动Hystrix可视化监控看板
@EnableEurekaClient
@EnableTurbine
@SpringBootApplication
public class TurbineApplication {
	
	@RequestMapping("/test")
	public String test() {
		return "turbine is ok";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TurbineApplication.class, args);
	}
}