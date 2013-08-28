package com.redhat.switchyard.feedback.service;

import com.redhat.switchyard.feedback.model.Feedback;

public interface OutgoingFeedbackService {
	
	public void sendMessage(Feedback feedback);

}
