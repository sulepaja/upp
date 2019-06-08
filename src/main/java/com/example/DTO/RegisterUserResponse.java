package com.example.DTO;

import com.example.model.User;

public class RegisterUserResponse {
	private User user;
	private String code;
	
	public RegisterUserResponse(User user, String code) {
		super();
		this.user = user;
		this.code = code;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
