package com.example.model;

import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User{
	
	private static final long serialVersionUID = 5119798847634486546L;
	private User user;
	
	public CurrentUser(User user){
		super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole()));
		this.user = user;		
	}
	
	public User getUser(){
		return this.user;
	}
	public Long getId(){
		return this.user.getId();
	}
	
	public String getRole(){
		return this.user.getRole();
	}
}
