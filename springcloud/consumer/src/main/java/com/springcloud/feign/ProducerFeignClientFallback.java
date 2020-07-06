package com.springcloud.feign;

import org.springframework.stereotype.Component;

@Component
class ProducerFeignClientFallback implements ProducerFeignClient {
	
	@Override
	public String test() {
		return "invoke fallback test method";
	}

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