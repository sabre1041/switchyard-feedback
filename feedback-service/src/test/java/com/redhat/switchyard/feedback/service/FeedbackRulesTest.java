package com.redhat.switchyard.feedback.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.component.test.mixins.hornetq.HornetQMixIn;
import org.switchyard.test.Invoker;
import org.switchyard.test.ServiceOperation;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;

import com.redhat.switchyard.feedback.model.Feedback;

/**
 * Validates the execution of the SwitchYard service invoking Drools rules
 * 
 * @author Andrew Block
 *
 */
@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = SwitchYardTestCaseConfig.SWITCHYARD_XML, mixins = { CDIMixIn.class, HornetQMixIn.class })
public class FeedbackRulesTest {

	@ServiceOperation("FeedbackRules.process")
	private Invoker service;
	

	@Test
	public void priorityTest() {
		Feedback feedback = new Feedback();
		feedback.setEmail("rsmith@redhat.com");
		service.sendInOnly(feedback);
		assertTrue(feedback.isPriority());
	}
	
	@Test
	public void invalidPriorityTest() {
		Feedback feedback = new Feedback();
		feedback.setEmail("jdoe@gmail.com");
		service.sendInOnly(feedback);
		assertFalse(feedback.isPriority());
	}
	
	@Test
	public void whitespacePriorityTest() {
		Feedback feedback = new Feedback();
		feedback.setEmail("swhite@redhat.com   ");
		service.sendInOnly(feedback);
		assertFalse(feedback.isPriority());
	}

	
}
