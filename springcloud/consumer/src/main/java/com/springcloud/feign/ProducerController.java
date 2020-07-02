package com.springcloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.springcloud.feign.ProducerController.HystrixClientFallback;

@FeignClient(name="producer",path="/producer",fallback=HystrixClientFallback.class)
public interface ProducerController {
	
	@PostMapping("/tests/{id}/{name}")
	public String save(@PathVariable("id") Integer id, @PathVariable("id")String name);
	
	@DeleteMapping("/tests/{id}")
	public String delete(@PathVariable("id") Integer id);
	
	@PutMapping("/tests/{id}/{name}")
	public String update(@PathVariable("id") Integer id, @PathVariable("id")String name);
	
	@GetMapping("/tests/{id}")
	public String productById(@PathVariable("id") Integer id);
	
	@GetMapping("/tests")
	public String products();
	
	static class HystrixClientFallback implements ProducerController {

		@Override
		public String save(Integer id, String name) {
			return "invoke fallback save method";
		}

		@Override
		public String delete(Integer id) {
			return "invoke fallback save delete";
		}

		@Override
		public String update(Integer id, String name) {
			return "invoke fallback update method";
		}

		@Override
		public String productById(Integer id) {
			return "invoke fallback productById method";
		}

		@Override
		public String products() {
			return "invoke fallback products method";
		}

	}

}
