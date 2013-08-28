package com.redhat.switchyard.feedback.service;

import java.util.List;

import com.redhat.switchyard.feedback.model.Feedback;

public interface FeedbackFileService {
	
	public void process(List<Feedback> feedbacks);

}
