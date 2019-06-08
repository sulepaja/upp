package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.model.CurrentUser;

@ControllerAdvice
public class CurrentUserControllerAdvice {
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserControllerAdvice.class);
	
	@ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(Authentication authentication) {
    	LOGGER.debug("CurrentUserControllerAdvice triggered.");
    	System.out.println("POZIVAM CURRENT USER CONTROLLER ADVICE");
        return (authentication == null) ? null : (CurrentUser) authentication.getPrincipal();
    }
}
