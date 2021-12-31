package com.stockMarket.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*",allowCredentials = "true")
public class APIController {
	
	@GetMapping("welcome")
	public String greet() {
		return "Welcome to Kirikalan Magic Show";
	}

}
