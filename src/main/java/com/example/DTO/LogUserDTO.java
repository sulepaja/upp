package com.example.DTO;

public class LogUserDTO {
	
	private String email;
	private String password;
	
	public LogUserDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public LogUserDTO(){}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
