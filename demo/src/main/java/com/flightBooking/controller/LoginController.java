package com.flightBooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightBooking.entities.User;
import com.flightBooking.services.UserService;

@RestController
//@CrossOrigin(origins = "http://localhost:9092")
//@RequestMapping("authenticate")
public class LoginController {
	@Autowired
	private UserService userService;

	@GetMapping("user/{name}")
	public User getUser(@PathVariable(name = "name") String name) {
		System.out.print("Name from Clientt"+name);
		User user = this.userService.getUser(name);
		System.out.print("User => "+user);
		return user;
	}
	
	@GetMapping("welcome")
	public String excep() {
		try {
			System.out.print("Exception => ");
		} catch (Exception e) {
			throw new ArithmeticException("Zero Divide");
		}
	
		return "HI";
	}
	
	@PostMapping("register")
	public User register(@RequestBody User user) {
		System.out.print("User data from client"+user);
		return this.userService.saveUser(user);
//		return user;
	}
}
