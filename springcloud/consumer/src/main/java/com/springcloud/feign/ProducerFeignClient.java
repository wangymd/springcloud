package com.springcloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

//@FeignClient(name="producer",fallback=ProducerFeignClientFallback.class)
@FeignClient(name="producer")
public interface ProducerFeignClient {
	
	@GetMapping("/test")
	public String test();
	
	@PostMapping("/tests/{id}/{name}")
	public String save(@PathVariable("id") Integer id, @PathVariable("name")String name);
	
	@DeleteMapping("/tests/{id}")
	public String delete(@PathVariable("id") Integer id);
	
	@PutMapping("/tests/{id}/{name}")
	public String update(@PathVariable("id") Integer id, @PathVariable("name")String name);
	
	@GetMapping("/tests/{id}")
	public String productById(@PathVariable("id") Integer id);
	
	@GetMapping("/tests")
	public String products();
}
