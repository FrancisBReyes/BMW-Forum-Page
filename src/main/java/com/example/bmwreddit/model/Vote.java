package com.example.bmwreddit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Vote {
	
	//FIELDS
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long voteId;
    private VoteType voteType;
    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    
    //CONSTRUCTOR 
    public Vote(Long voteId, VoteType voteType, @NotNull Post post, User user) {
		super();
		this.voteId = voteId;
		this.voteType = voteType;
		this.post = post;
		this.user = user;
	}
    
    //GETTER
	public Long getVoteId() {
		return voteId;
	}
	
	//SETTER
	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}
	
	//GETTER
	public VoteType getVoteType() {
		return voteType;
	}
	
	//SETTER
	public void setVoteType(VoteType voteType) {
		this.voteType = voteType;
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
	public User getUser() {
		return user;
	}
	
	//SETTER
	public void setUser(User user) {
		this.user = user;
	}
    
    
}
