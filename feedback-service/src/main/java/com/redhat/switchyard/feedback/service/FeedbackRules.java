package com.redhat.switchyard.feedback.service;

import com.redhat.switchyard.feedback.model.Feedback;

/**
 * Interface for invoking Drools Rules
 * 
 * @author Andrew Block
 *
 */
public interface FeedbackRules {
	
	public void process(Feedback feedback);

}
