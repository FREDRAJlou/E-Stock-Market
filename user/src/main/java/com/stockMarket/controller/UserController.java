package com.stockMarket.controller;


import com.stockMarket.entities.User;
import com.stockMarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

	@Autowired
	private UserService userService;


	@GetMapping("user/{userName}")
	public User getUserByName(@PathVariable String userName) {
		return this.userService.getUserByName(userName);
	}

	@PostMapping("saveUser")
	public void saveUser(@RequestBody User user) {
		 this.userService.saveUser(user);
	}

}
