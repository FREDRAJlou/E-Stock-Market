package com.flightBooking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler{
	
	@ExceptionHandler
	public ResponseEntity<String> handleException(Exception e){
		return ResponseEntity.ok("error");
	}

	
}
