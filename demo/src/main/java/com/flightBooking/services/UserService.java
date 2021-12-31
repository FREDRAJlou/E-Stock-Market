package com.flightBooking.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flightBooking.entities.User;
import com.flightBooking.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserRepository userRepo;
	
	public User saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
	
	public User getUser(String name) {
		Optional<User> user = userRepo.findByName(name);
		if(user.isPresent())
			return user.get();
		return new User(); 
	}
}
