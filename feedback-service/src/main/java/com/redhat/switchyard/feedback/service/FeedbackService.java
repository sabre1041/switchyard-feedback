package com.redhat.switchyard.feedback.service;

import com.redhat.switchyard.feedback.model.Feedback;


/**
 * Interface for the Feedback Service
 * 
 * @author Andrew Block
 *
 */
public interface FeedbackService {
	
	public void process(Feedback feedback);

}
