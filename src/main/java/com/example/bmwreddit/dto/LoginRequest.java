package com.example.bmwreddit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

//DEFINE REST ENDPOINT FOR LOGIN 

@Data
@AllArgsConstructor 
public class LoginRequest {

	private String username; 
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
	
	
}
