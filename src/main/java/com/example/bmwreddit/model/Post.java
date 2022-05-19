package com.example.bmwreddit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	
    //FIELDS
	@Id
    @GeneratedValue(strategy = IDENTITY)
    private Long postId;
    @NotBlank(message = "Post Name cannot be empty or Null")
    private String postName;
    @Nullable
    private String url;
    @Nullable
    @Lob
    private String description;
    private Integer voteCount = 0;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    private Instant createdDate;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Subreddit subreddit;
    
    //CONSTRUCTOR 
	public Post(Long postId, @NotBlank(message = "Post Name cannot be empty or Null") String postName, String url,
			String description, Integer voteCount, User user, Instant createdDate, Subreddit subreddit) {
		super();
		this.postId = postId;
		this.postName = postName;
		this.url = url;
		this.description = description;
		this.voteCount = voteCount;
		this.user = user;
		this.createdDate = createdDate;
		this.subreddit = subreddit;
	}

	//GETTER
	public Long getPostId() {
		return postId;
	}

	//SETTER
	public void setPostId(Long postId) {
		this.postId = postId;
	}

	//GETTER
	public String getPostName() {
		return postName;
	}

	//SETTER
	public void setPostName(String postName) {
		this.postName = postName;
	}

	//GETTER
	public String getUrl() {
		return url;
	}

	//SETTER
	public void setUrl(String url) {
		this.url = url;
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
	public Integer getVoteCount() {
		return voteCount;
	}

	//SETTER
	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
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
	public Instant getCreatedDate() {
		return createdDate;
	}

	//SETTER
	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	//GETER
	public Subreddit getSubreddit() {
		return subreddit;
	}

	//SETTER
	public void setSubreddit(Subreddit subreddit) {
		this.subreddit = subreddit;
	}
	
	
    
    
}
