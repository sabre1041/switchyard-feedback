package com.redhat.switchyard.feedback.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.component.test.mixins.hornetq.HornetQMixIn;
import org.switchyard.test.BeforeDeploy;
import org.switchyard.test.MockHandler;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.test.SwitchYardTestKit;
import org.switchyard.test.mixins.PropertyMixIn;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = SwitchYardTestCaseConfig.SWITCHYARD_XML, mixins = { CDIMixIn.class, HornetQMixIn.class, PropertyMixIn.class })
public class FeedbackSmooksFileTest {
		
	private SwitchYardTestKit testKit;

    private static String SOURCE_FILE = "target/test-classes/feedback.txt";
    private static String DEST_FILE = "target/files/input/feedback.txt";

    private PropertyMixIn properties;
    
    @BeforeDeploy
    public void setUp() {
        properties.set("prop.filedir.input", "target/files/input/");
        properties.set("prop.filedir.completed", "../completed/");
    }
	
	@Test
	public void smooksTest() throws InterruptedException, IOException {
		
        MockHandler camel = testKit.replaceService("FeedbackService").forwardInToOut();
		Thread.sleep(2000);
        
		File srcFile = new File(SOURCE_FILE);
		File destFile = new File(DEST_FILE);
		
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }

		FileUtils.copyFile(srcFile,destFile);
		
		Thread.sleep(5000);
		
        assertEquals(2,camel.getMessages().size());	

		
//        Exchange exchange = camel.getMessages().peek();
//		
//		List<Feedback> outgoingFeedback = (List<Feedback>) exchange.getMessage().getContent();
//		assertNotNull(outgoingFeedback);
	


        
	}
	
	

	
}
