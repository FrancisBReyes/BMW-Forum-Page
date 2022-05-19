package com.example.bmwreddit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    
	//FIELDS 
	@Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotEmpty
    private String text;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;
    private Instant createdDate;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    
    //CONSTRUCTOR 
	public Comment(Long id, @NotEmpty String text, Post post, Instant createdDate, User user) {
		super();
		this.id = id;
		this.text = text;
		this.post = post;
		this.createdDate = createdDate;
		this.user = user;
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
	public String getText() {
		return text;
	}
	
	//SETTER
	public void setText(String text) {
		this.text = text;
	}
	
	//GETTER
	public Post getPost() {
		return post;
	}
	
	//SETTER
	public void setPost(Post post) {
		this.post = post;
	}
	
	//GETTER
	public Instant getCreatedDate() {
		return createdDate;
	}
	
	//SETTER
	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}
	
	//GETTER
	public User getUser() {
		return user;
	}
	
	//SETTER
	public void setUser(User user) {
		this.user = user;
	}
    
    
}
