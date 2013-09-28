package com.redhat.switchyard.feedback.manager;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * RestEasy Application class
 * 
 * @author Andrew Block
 *
 */
@ApplicationPath("rest")
@ApplicationScoped
public class FeedbackManager extends Application {

}
