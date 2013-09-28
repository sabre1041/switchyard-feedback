package com.redhat.switchyard.feedback.resource;

import javax.jms.JMSException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.redhat.switchyard.feedback.model.Feedback;

/**
 * RestEasy service interface
 * 
 * @author Andrew Block
 *
 */
@Path("feedback")
public interface FeedbackService {
	
	/**
	 * Submission of a Feedback object to the Feedback Service
	 * 
	 * @param feedback the feedback object
	 * @return 
	 * @throws JMSException
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response submit(Feedback feedback) throws JMSException;
	

}
