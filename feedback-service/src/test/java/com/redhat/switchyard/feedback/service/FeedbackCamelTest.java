package com.redhat.switchyard.feedback.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.component.test.mixins.hornetq.HornetQMixIn;
import org.switchyard.test.Invoker;
import org.switchyard.test.MockHandler;
import org.switchyard.test.ServiceOperation;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.test.SwitchYardTestKit;

import com.redhat.switchyard.feedback.model.Feedback;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = SwitchYardTestCaseConfig.SWITCHYARD_XML, mixins = { CDIMixIn.class, HornetQMixIn.class })
public class FeedbackCamelTest {

	@ServiceOperation("FeedbackFileService.process")
	private Invoker service;
		
	private SwitchYardTestKit testKit;
		
	@Test
	public void camelTest() throws InterruptedException {
		List<Feedback> feedbacks = new ArrayList<Feedback>();
		
		Feedback feedback = new Feedback();
		feedback.setEmail("testemail1@redhat.com");
		feedbacks.add(feedback);
		
		Feedback feedback2 = new Feedback();
		feedback2.setEmail("testemail2@redhat.com");
		feedbacks.add(feedback2);
		
		
        MockHandler camel = testKit.replaceService("FeedbackService").forwardInToOut();
		service.sendInOnly(feedbacks);
		Thread.sleep(2000);
        
        assertEquals(2,camel.getMessages().size());	


        
	}
	
	

	
}
