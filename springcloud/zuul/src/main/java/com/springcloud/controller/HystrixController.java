package com.springcloud.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class HystrixController {
	
	@HystrixCommand(fallbackMethod="fallbackMethod")
	@RequestMapping("/hystrixTest")
	public String hystrixTest() {
		return "hystrix is ok";
	}
	
	public String fallbackMethod() {
		return "fallbackMethod is ok";
	}
	
	private Random random = new Random(1);
	
	@HystrixCommand(
			fallbackMethod="fallbackMethod2",
			commandProperties={
					//超时设置
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="500")
			}
	)
	@RequestMapping("/hystrixTest2/{msg}")
	public String hystrixTest2(@PathVariable(name="msg", required=true)String msg) {
		try {
			int nextInt = random.nextInt(100);
			System.out.println(nextInt);
			if(nextInt % 2 == 0) Thread.sleep(1000);
			else Thread.sleep(450);
		} catch (Exception e) {
		}
		return "hystrixTest2 is ok" + ",params is " + msg;
	}
	
	public String fallbackMethod2(String msg) {
		return "fallbackMethod2 is ok" + ",params is " + msg;
	}
	
	@HystrixCommand(
			fallbackMethod="fallbackMethod3",
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
	@RequestMapping("/hystrixTest3/{msg}")
	public String hystrixTest3(@PathVariable(name="msg", required=true)String msg) {
		return "hystrixTest3 is ok" + ",params is " + msg;
	}
	
	public String fallbackMethod3(String msg) {
		return "fallbackMethod3 is ok" + ",params is " + msg;
	}

}
