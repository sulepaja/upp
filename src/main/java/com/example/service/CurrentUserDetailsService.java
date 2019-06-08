package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.model.CurrentUser;
import com.example.model.User;

@Service
public class CurrentUserDetailsService implements UserDetailsService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailsService.class);
	
	@Autowired
	private UserService userService;;
	
	@Override
	public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
		LOGGER.debug("Authenticating user with email={}", email);
		System.out.println("POZIVAM CURRENT USER DETAIL SERVICE");
		User user = userService.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", email)));
		return new CurrentUser(user);
	}

}
