package com.flightBooking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightBooking.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByName(String name);
}
