package com.example.service;

import java.util.Optional;

import com.example.model.User;

public interface UserService {

	
	Optional<User> findByEmail(String email);
	User register(User u);
	Optional<User> findByUsername(String username);

//	User save(User u);

}
