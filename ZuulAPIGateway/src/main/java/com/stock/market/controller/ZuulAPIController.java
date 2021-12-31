package com.stock.market.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1.0/market")
@CrossOrigin(origins = "*",allowedHeaders = "*",allowCredentials = "true")
public class ZuulAPIController {
	
	@GetMapping("welcome")
	public String greet() {
		return "Welcome to Kirikalan Magic Show";
	}

}
