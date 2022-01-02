package com.stockMarket.controller;


import com.stockMarket.entities.User;
import com.stockMarket.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class UserController {

	@Autowired
	private UserService userService;


	@GetMapping("user/{userName}")
	@ApiOperation(value = "get User Details", notes = "retrieves User Details")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "User Details retrieved successfully"),
			@ApiResponse(code = 400, message = "Exception occurred while retrieving User Details")})
	public User getUserByName(@PathVariable String userName) {
		return this.userService.getUserByName(userName);
	}


	@GetMapping("validateUser/{userName}/{password}")
	@ApiOperation(value = "validate User Details", notes = "validates User Details")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "User Details validated successfully"),
			@ApiResponse(code = 400, message = "Exception occurred while validating User Details")})
	public String validateUser(@PathVariable("userName") String userName,@PathVariable("password") String password) {
		return this.userService.validateUser(userName,password)?"Valid":"Invalid";
	}

	@PostMapping("saveUser")
	@ApiOperation(value = "save User Details", notes = "save User Details")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "User saved successfully"),
			@ApiResponse(code = 400, message = "Exception occurred while saving user details")})
	public String saveUser(@RequestBody User user) {
		 return Objects.nonNull(this.userService.saveUser(user))?"Saved":"Failed";
	}

}
