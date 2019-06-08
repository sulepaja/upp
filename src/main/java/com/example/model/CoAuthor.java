package com.example.model;

public class CoAuthor {
	private Long id;
	private String name;
	private String lastname;
	private String city;
	private String state;
	private String email;
	private String password;
	
	public CoAuthor(){}
	
	public CoAuthor(String name, String lastname, String city, String state, String email, String password) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.city = city;
		this.state = state;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

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
