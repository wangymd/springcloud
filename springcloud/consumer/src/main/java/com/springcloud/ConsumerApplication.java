package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;

@EnableFeignClients //开启feign客户端
@EnableEurekaClient
@SpringBootApplication
public class ConsumerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}
	
	@Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
 
    @Bean
    public IRule ribbonRule() {
        return new BestAvailableRule();	//这里配置策略，和配置文件对应
    }
}