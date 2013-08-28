package com.redhat.switchyard.feedback.service;

import com.redhat.switchyard.feedback.model.Feedback;


public interface FeedbackService {
	
	public void process(Feedback feedback);

}
