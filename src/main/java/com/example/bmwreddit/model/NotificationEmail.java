package com.example.bmwreddit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //LOMBOK --> GETTERS & SETTERS --> CONSTRUCTOR
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEmail {
	
	//CONSTRUCTOR
    public NotificationEmail(String subject, String recipient, String body) {
		super();
		this.subject = subject;
		this.recipient = recipient;
		this.body = body;
	}
    
    //FIELDS
	private String subject;
    private String recipient;
    private String body;
    
    //GETTER
	public String getSubject() {
		return subject;
	}
	//SETTER
	public void setSubject(String subject) {
		this.subject = subject;
	}
	//GETTER
	public String getRecipient() {
		return recipient;
	}
	//SETTER
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	//GETTER
	public String getBody() {
		return body;
	}
	//SETTER
	public void setBody(String body) {
		this.body = body;
	}
    
    
}
