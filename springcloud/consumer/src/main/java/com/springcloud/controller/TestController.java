package com.springcloud.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springcloud.feign.ProducerFeignClient;

@RestController
public class TestController {
	
	@Autowired
	ProducerFeignClient producerFeignClient;
	
	@Autowired
	LoadBalancerClient LoadBalancerClient;
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/test")
	public String test() {
		return producerFeignClient.test();
	}
	
	@RequestMapping("/test2")
	public String test2() {
        ServiceInstance instance = LoadBalancerClient.choose("PRODUCER");
        URI uri = URI.create(String.format("http://%s:%s/test", instance.getHost(), instance.getPort()));
        
        String rt = restTemplate.getForObject(uri, String.class);
        return rt;
	}
	
	@PostMapping("/tests/{id}/{name}")
	public String save(@PathVariable("id") Integer id, @PathVariable("name")String name) {
		return producerFeignClient.save(id, name);
	}
	
	@DeleteMapping("/tests/{id}")
	public String delete(@PathVariable("id") Integer id) {
		return producerFeignClient.delete(id);
	}
	
	@PutMapping("/tests/{id}/{name}")
	public String update(@PathVariable("id") Integer id, @PathVariable("name")String name) {
		return producerFeignClient.update(id, name);
	}
	
	@GetMapping("/tests/{id}")
	public String productById(@PathVariable("id") Integer id) {
		return producerFeignClient.productById(id);
	}
	
	@GetMapping("/tests")
	public String products() {
		return producerFeignClient.products();
	}

}
