package de.automation.skl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Component
@Named("loggingA")
public class LoggingA implements JavaDelegate {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingA.class);
    
	@Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
  	  System.out.println("Service Task Logging A executed");
  	  LOGGER.info("doStuff in ServiceTask \"LoggingA\" - {}");
      LOGGER.error("doStuff encountered an error in ServiceTask \"LoggingA\" - {}");

    }
}
