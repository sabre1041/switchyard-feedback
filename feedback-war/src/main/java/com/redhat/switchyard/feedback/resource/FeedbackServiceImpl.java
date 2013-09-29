package com.redhat.switchyard.feedback.resource;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.switchyard.feedback.model.Feedback;

/**
 * Feedback RestEasy service. Exposes restful endpoint and send to a JMS queue for 
 * SwitchYard service processing
 * 
 * @author Andrew Block
 *
 */
public class FeedbackServiceImpl implements FeedbackService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceImpl.class);
	
    @Resource(mappedName = "java:/queue/switchyardFeedbackQueue")
    Queue feedbackDest;
    
    @Resource(mappedName = "java:/ConnectionFactory")
    QueueConnectionFactory connectionFactory;
    
    Connection connection;
	
	@Override
	public Response submit(Feedback feedback) {
		
		try {
			
			submitFeedbackToQueue(feedback);
	
			return Response.ok().build();
		
		}
		catch(Exception e) {
			LOGGER.error(e.getMessage(), e);
			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	
	 @PostConstruct
	    public void postConstruct() {
	        try {

	            connection = connectionFactory.createConnection();
	        }
	        catch (JMSException ex) {

	            LOGGER.error(ex.getMessage(), ex);
	        }
	    }

	    @PreDestroy
	    public void preDestroy() {

	        try {

	            connection.close();
	        }
	        catch (JMSException ex) {

	            LOGGER.error(ex.getMessage(), ex);
	        }
	    }

	    private void submitFeedbackToQueue(Feedback feedback) throws JMSException {

	        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

	        try {

	            ObjectMessage message = session.createObjectMessage(feedback);

	            MessageProducer producer = session.createProducer(feedbackDest);

	            producer.send(message);
	        }
	        finally {

	            session.close();
	        }
	    }



}
