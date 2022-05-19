package com.example.bmwreddit.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Subreddit {
	
	//FIELDS
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotBlank(message = "Community name is required")
    private String name;
    @NotBlank(message = "Description is required")
    private String description;
    @OneToMany(fetch = LAZY)
    private List<Post> posts;
    private Instant createdDate;
    @ManyToOne(fetch = LAZY)
    private User user;
    
    //CONSTRUCTOR
    public Subreddit(Long id, @NotBlank(message = "Community name is required") String name,
			@NotBlank(message = "Description is required") String description, List<Post> posts, Instant createdDate,
			User user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.posts = posts;
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
	public String getName() {
		return name;
	}
	
	//SETTER
	public void setName(String name) {
		this.name = name;
	}
	
	//GETTER
	public String getDescription() {
		return description;
	}
	
	//SETTER
	public void setDescription(String description) {
		this.description = description;
	}
	
	//GETTER
	public List<Post> getPosts() {
		return posts;
	}
	
	//SETTER
	public void setPosts(List<Post> posts) {
		this.posts = posts;
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

