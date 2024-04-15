package de.automation.skl;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import jakarta.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Component
@Named("loggingA")
public class LoggingA implements JavaDelegate {
    private static final Logger LOGGER = null;

	@Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
  	  System.out.println("New item with number:");
  	  LOGGER.info("doStuff the desired logging - {}");
      LOGGER.error("doStuff encountered an error with value - {}");

    }
}
