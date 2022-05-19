package com.example.bmwreddit.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	
    //EMPTY CONSTRUCTOR
	public User() {
	}
	
	//CONSTRUCTOR
	public User(Long userId, @NotBlank(message = "Username is required") String username,
			@NotBlank(message = "Password is required") String password,
			@Email @NotEmpty(message = "Email is required") String email, Instant created, boolean enabled) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.created = created;
		this.enabled = enabled;
	}

	//FIELDS
	@Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long userId;
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
    @Email
    @NotEmpty(message = "Email is required")
    private String email;
    private Instant created;
    private boolean enabled;
    
	//GETTER
	public Long getUserId() {
		return userId;
	}
	
	//SETTER
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	//GETTER
	public String getUsername() {
		return username;
	}
	
	//SETTER
	public void setUsername(String username) {
		this.username = username;
	}
	
	//GETTER
	public String getPassword() {
		return password;
	}
	
	//SETTER
	public void setPassword(String password) {
		this.password = password;
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
	public Instant getCreated() {
		return created;
	}
	
	//SETTER
	public void setCreated(Instant created) {
		this.created = created;
	}
	
	//GETTER
	public boolean isEnabled() {
		return enabled;
	}
	
	//SETTER
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
    
}

