package de.automation.skl;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.inject.Named;

@Component
@Named("serviceA")
public class ServiceA implements JavaDelegate {

private static final Logger LOGGER = LoggerFactory.getLogger(ServiceA.class);
    
	@Override
    public void execute(DelegateExecution execution) throws Exception {
   
        System.out.println("Service Task Service A executed");


        HttpResponse<String> response = get("http://monitoring-svc:/");

        }

    public HttpResponse<String> get(String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        	
        LOGGER.info("ServiceCall montoringService: \n" +  response.body());
        return response;
    }
}