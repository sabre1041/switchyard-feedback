package com.redhat.switchyard.feedback.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Feedback model object
 * 
 * @author Andrew Block
 *
 */
@XmlRootElement
public class Feedback implements Serializable {
	
	private String comment;
	private String email;
	private boolean priority;
	private boolean subscription;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isPriority() {
		return priority;
	}

	public void setPriority(boolean priority) {
		this.priority = priority;
	}

	public boolean isSubscription() {
		return subscription;
	}

	public void setSubscription(boolean subscription) {
		this.subscription = subscription;
	}

}
