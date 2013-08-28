package com.redhat.switchyard.feedback.resource;

import javax.jms.JMSException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.redhat.switchyard.feedback.model.Feedback;

@Path("feedback")
public interface FeedbackService {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response submit(Feedback feedback) throws JMSException;
	
	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test();

}
