package com.example.controller;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ExceptionHandlerControllerAdvice {
	private static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerControllerAdvice.class);

	@ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoSuchElementException(NoSuchElementException e) {
    	LOGGER.debug("ExceptionHandlerControllerAdvice triggered.");
        return e.getMessage();
    }
}
