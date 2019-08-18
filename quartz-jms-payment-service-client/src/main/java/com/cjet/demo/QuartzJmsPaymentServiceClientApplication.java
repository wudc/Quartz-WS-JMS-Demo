package com.cjet.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cjet.demo.saop.client.SOAPConnector;
import com.cjet.demo.schemas.payments.GetPaymentRequest;
import com.cjet.demo.schemas.payments.GetPaymentResponse;

@SpringBootApplication
public class QuartzJmsPaymentServiceClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuartzJmsPaymentServiceClientApplication.class, args);
	}

	@Bean
    CommandLineRunner lookup(SOAPConnector soapConnector) {
        return args -> {

            GetPaymentRequest rejected = new GetPaymentRequest();
            rejected.setAmount(15000.0f);
            rejected.setStatus("Submitted");
            rejected.setMethod("Cash");

            GetPaymentResponse rejectedResponse =(GetPaymentResponse) soapConnector.callWebService("http://localhost:8080/ws", rejected);
            System.out.println("Got Response As below ========= : ");
            System.out.println("Id : "+rejectedResponse.getAcknowledgement().getId());
            System.out.println("Acknowledgement : "+rejectedResponse.getAcknowledgement().getAcknowledgement());
        	
            GetPaymentRequest pending = new GetPaymentRequest();
            pending.setAmount(15000.0f);
            pending.setStatus("Submitted");
            pending.setMethod("Credit");

            GetPaymentResponse pendingResponse =(GetPaymentResponse) soapConnector.callWebService("http://localhost:8080/ws", pending);
            System.out.println("Got Response As below ========= : ");
            System.out.println("Id : "+pendingResponse.getAcknowledgement().getId());
            System.out.println("Acknowledgement : "+pendingResponse.getAcknowledgement().getAcknowledgement());

            GetPaymentRequest accepted = new GetPaymentRequest();
            accepted.setAmount(5000.0f);
            accepted.setStatus("Submitted");
            accepted.setMethod("Cash");

            GetPaymentResponse acceptedResponse =(GetPaymentResponse) soapConnector.callWebService("http://localhost:8080/ws", accepted);
            System.out.println("Got Response As below ========= : ");
            System.out.println("Id : "+acceptedResponse.getAcknowledgement().getId());
            System.out.println("Acknowledgement : "+acceptedResponse.getAcknowledgement().getAcknowledgement());
                        
        };
    }	
}
