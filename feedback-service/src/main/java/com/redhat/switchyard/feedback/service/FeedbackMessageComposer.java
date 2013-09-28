package com.redhat.switchyard.feedback.service;

import org.switchyard.Exchange;
import org.switchyard.Message;
import org.switchyard.component.camel.common.composer.CamelBindingData;
import org.switchyard.component.camel.common.composer.CamelMessageComposer;

import com.redhat.switchyard.feedback.model.Feedback;

/**
 * Custom Camel Message Composer which allows for the dynamic assignment of the "to" and "priority"
 * fields of an Email. 
 * 
 * @author Andrew Block
 * @see CamelMessageComposer
 *
 */
public class FeedbackMessageComposer extends CamelMessageComposer {

	private static final String TO_PROP = "to";
	private static final String PRIORITY = "X-Priority";
	private static final String PRIORITY_VALUE = "1";
	private static final String RESPONSE_MESSAGE = "Thank you for subscribing for our newsletter! The " +
			"comments you made will be used to make our products even better. \n\nComment: %s\n\nThanks";
	
	
	@Override
    public CamelBindingData decompose(Exchange exchange, CamelBindingData target) throws Exception {

		Message sourceMessage = exchange.getMessage();
        
        Object object = sourceMessage.getContent();
        
        if(object instanceof Feedback) {
        	
        	Feedback feedback = (Feedback) object;
        	
        	sourceMessage.getContext().setProperty(TO_PROP, feedback.getEmail());
        	
        	if(feedback.isPriority()) {
        		sourceMessage.getContext().setProperty(PRIORITY, PRIORITY_VALUE);
        	}
        	
        	sourceMessage.setContent(String.format(RESPONSE_MESSAGE, feedback.getComment()));
        	
        	
        }
		
		return super.decompose(exchange, target);
    }

}
