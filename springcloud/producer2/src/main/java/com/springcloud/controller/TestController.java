package com.springcloud.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.pojo.Product;

@RestController
public class TestController {
	
	private Map<Integer,Product> datas = new HashMap<Integer,Product>();
	
	@PostMapping("/tests/{id}/{name}")
	public String save(@PathVariable("id") Integer id, @PathVariable("name")String name) {
		Product product = new Product(id,name);
		datas.put(id, product);
		return "save is ok";
	}
	
	@DeleteMapping("/tests/{id}")
	public String delete(@PathVariable("id") Integer id) {
		datas.remove(id);
		return "delete is ok";
	}
	
	@PutMapping("/tests/{id}/{name}")
	public String update(@PathVariable("id") Integer id, @PathVariable("name")String name) {
		Product product = datas.get(id);
		product.setName(name);
		datas.put(id, product);
		return "update is ok";
	}
	
	@GetMapping("/tests/{id}")
	public String productById(@PathVariable("id") Integer id) {
		Product product = datas.get(id);
		return product.toString();
	}
	
	@HystrixCommand(
			fallbackMethod="fallbackMethod",
			commandProperties={
					//超时设置
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="500")
			},
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "5"),
                    @HystrixProperty(name = "maxQueueSize", value = "10"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "1"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "10"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
    }
	)
	@GetMapping("/tests")
	public String products() {
		Collection<Product> values = datas.values();
		return values.toString();
	}
	
	public String fallbackMethod() {
		return "producer2 service tests method fallbackMethod";
	}

}
