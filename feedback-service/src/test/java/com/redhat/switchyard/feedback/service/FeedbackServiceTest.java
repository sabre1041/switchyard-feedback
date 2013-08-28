package com.redhat.switchyard.feedback.service;

import static org.junit.Assert.assertTrue;

import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.Exchange;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.component.test.mixins.hornetq.HornetQMixIn;
import org.switchyard.component.test.mixins.smooks.SmooksMixIn;
import org.switchyard.test.MockHandler;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.test.SwitchYardTestKit;

import com.redhat.switchyard.feedback.model.Feedback;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = SwitchYardTestCaseConfig.SWITCHYARD_XML, mixins = {
		CDIMixIn.class, HornetQMixIn.class, SmooksMixIn.class})
public class FeedbackServiceTest {

	private SwitchYardTestKit testKit;
	private CDIMixIn cdiMixIn;
	private HornetQMixIn hornetQMixIn;
	
	@Test
	public void testProcess() throws Exception {
				
        MockHandler mail = testKit.replaceService("OutgoingFeedbackService").forwardInToOut();
        
        MessageProducer producer = hornetQMixIn.getJMSSession().createProducer(HornetQMixIn.getJMSQueue("switchyardFeedback"));	

        
		Feedback message = new Feedback();
		message.setEmail("ablock@redhat.com");
		message.setComment("This is a message from the switchyard test program");
		message.setSubscription(true);
        final ObjectMessage msg = hornetQMixIn.getJMSSession().createObjectMessage();
        msg.setObject(message);
        
        producer.send(msg);
        
        // Sleep for a few seconds
        Thread.sleep(2000);

		Assert.assertEquals(1, mail.getMessages().size());
		Exchange exchange = mail.getMessages().peek();
		Feedback outgoingFeedback = (Feedback) exchange.getMessage().getContent();
		assertTrue(outgoingFeedback.isPriority());
	}

}
