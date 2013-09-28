package com.redhat.switchyard.feedback.service;

import java.util.List;

import com.redhat.switchyard.feedback.model.Feedback;

/**
 * Interface for the FeedbackFileService
 * 
 * @author Andrew Block
 *
 */
public interface FeedbackFileService {
	
	public void process(List<Feedback> feedbacks);

}
