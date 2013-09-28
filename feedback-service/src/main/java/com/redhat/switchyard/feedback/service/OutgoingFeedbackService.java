package com.redhat.switchyard.feedback.service;

import com.redhat.switchyard.feedback.model.Feedback;

/**
 * Interface for the OutgoingFeedbackService
 * 
 * @author Andrew Block
 *
 */
public interface OutgoingFeedbackService {
	
	public void sendMessage(Feedback feedback);

}
