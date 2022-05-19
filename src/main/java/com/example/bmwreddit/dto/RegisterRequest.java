package com.example.bmwreddit.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

//DATA TRANSFER OBJECT --> AUTHCONTROLLER

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	
	//CONSTRUCTOR 
	@Autowired
	public RegisterRequest(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	//EMPTY CONSTRUCTOR 
	public RegisterRequest() {
	}

	//FIELDS
	//@NotNull
	private String username;
	//@NotNull
	//@Email
    private String email;
	//@NotNull
    private String password;
    
    //GETTER
	public String getUsername() {
		return username;
	}
	
	//SETTER
	public void setUsername(String username) {
		this.username = username;
	}
	
	//GETTER
	public String getEmail() {
		return email;
	}
	
	//SETTER
	public void setEmail(String email) {
		this.email = email;
	}
	
	//GETTER
	public String getPassword() {
		return password;
	}
	
	//SETTER
	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
