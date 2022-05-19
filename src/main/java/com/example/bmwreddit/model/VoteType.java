package com.example.bmwreddit.model;

//NEED TO MAKE EXCEPTIONS PACKAGE
//import com.programming.techie.springredditclone.exceptions.SpringRedditException;

import java.util.Arrays;

import com.example.bmwreddit.exception.SpringRedditException;

public enum VoteType {
    UPVOTE(1), DOWNVOTE(-1),
    ;

    private int direction;

    VoteType(int direction) {
    	
    }

    //EXCEPTION --> CONSTRUCTOR 
    public static VoteType lookup(Integer direction) {
        return Arrays.stream(VoteType.values())
                .filter(value -> value.getDirection().equals(direction))
                .findAny()
                .orElseThrow(() -> new SpringRedditException("Vote not found"));
    } 

    //GETTER
    public Integer getDirection() {
        return direction;
    }

	//SETTER
	public void setDirection(int direction) {
		this.direction = direction;
	}
    
    
}
