package com.springcloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class htmlController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
}