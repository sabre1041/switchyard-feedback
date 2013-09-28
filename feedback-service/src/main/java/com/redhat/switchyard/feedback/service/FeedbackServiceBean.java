package com.redhat.switchyard.feedback.service;

import javax.inject.Inject;

import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;

import com.redhat.switchyard.feedback.model.Feedback;

/**
 * SwitchYard Bean Implementation which demonstrates invoking service references. First,
 * a Drools Rules service is invoked. Afterwards, the Email Service is invoked if a 
 * subscription is selected on the {@link Feedback} object
 * 
 * @author Andrew Block
 *
 */
@Service(FeedbackService.class)
public class FeedbackServiceBean implements FeedbackService {
	
	@Inject @Reference
	FeedbackRules feedbackRules;
	
	@Inject @Reference
	OutgoingFeedbackService outgoingFeedbackService;

		

	@Override
	public void process(Feedback feedback) {

		System.out.println("Inside Switchyard Bean");
		System.out.println("Email: "+feedback.getEmail());
		System.out.println("Comment: "+feedback.getComment());
						
		// Call Drools Rules
		feedbackRules.process(feedback);
		
		// Send Email if subscription
		if(feedback.isSubscription()) {
			outgoingFeedbackService.sendMessage(feedback);	
		}
	}

}
