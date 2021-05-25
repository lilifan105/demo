package com.example.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AccountForm {

	private long id;
	
	@NotBlank
	@Size(min=4, max=255)
	private String username;
	
	@NotBlank
	@Size(min=8)
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
}
