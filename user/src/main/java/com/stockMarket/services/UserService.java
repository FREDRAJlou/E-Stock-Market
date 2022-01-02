package com.stockMarket.services;

import com.stockMarket.entities.User;
import com.stockMarket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getUserByName(String name){
	   return this.userRepository.findByName(name);
    }

	public User saveUser(User user){
		Date now = new Date();
		user.setDate(now);
		user.setOffset(now.getTimezoneOffset());
	    return this.userRepository.save(user);
    }

	public boolean validateUser(String userName, String password) {
		User user = this.getUserByName(userName);
		if (Objects.nonNull(user)&&Objects.nonNull(user.getPassword())) {
			return user.getPassword().equals(password);
		}
		return false;
	}
}