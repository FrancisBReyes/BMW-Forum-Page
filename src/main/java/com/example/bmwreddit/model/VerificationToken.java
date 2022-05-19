package com.example.bmwreddit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "token")
public class VerificationToken {

	//FIELDS
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String token;
    @OneToOne(fetch = LAZY)
    private User user;
    private Instant expiryDate;
    
    //CONSTRUCTOR
    public VerificationToken(Long id, String token, User user, Instant expiryDate) {
		super();
		this.id = id;
		this.token = token;
		this.user = user;
		this.expiryDate = expiryDate;
	}

	//GETTER
	public Long getId() {
		return id;
	}
	
	//SETTER
	public void setId(Long id) {
		this.id = id;
	}
	
	//GETTER
	public String getToken() {
		return token;
	}
	
	//SETTER
	public void setToken(String token) {
		this.token = token;
	}
	
	//GETTER
	public User getUser() {
		return user;
	}
	
	//SETTER
	public void setUser(User user) {
		this.user = user;
	}
	
	//GETTER
	public Instant getExpiryDate() {
		return expiryDate;
	}
	
	//SETTER
	public void setExpiryDate(Instant expiryDate) {
		this.expiryDate = expiryDate;
	}
    
    
}
