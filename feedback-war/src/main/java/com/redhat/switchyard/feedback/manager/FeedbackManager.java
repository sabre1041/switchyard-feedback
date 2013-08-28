package com.redhat.switchyard.feedback.manager;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("rest")
@ApplicationScoped
public class FeedbackManager extends Application {

}
