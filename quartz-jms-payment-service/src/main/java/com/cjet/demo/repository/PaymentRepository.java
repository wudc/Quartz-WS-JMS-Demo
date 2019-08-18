package com.cjet.demo.repository;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import https.www_cjet_com.xml.payment.Acknowledgement;

@Component
public class PaymentRepository {
	private static final Map<String, Acknowledgement> acknowledgements = new HashMap<>();
	
	@PostConstruct
    public void initData() {
		
		Acknowledgement accepted = new Acknowledgement();
		accepted.setAcknowledgement("Accepted");
		accepted.setId(1L);
		acknowledgements.put(accepted.getAcknowledgement(), accepted);

		Acknowledgement rejected = new Acknowledgement();
		rejected.setAcknowledgement("Rejected");
		rejected.setId(2L);
		acknowledgements.put(rejected.getAcknowledgement(), rejected);
		
		Acknowledgement pending = new Acknowledgement();
		pending.setAcknowledgement("Pending");
		pending.setId(3L);
		acknowledgements.put(pending.getAcknowledgement(), pending);
			
	}
	
	public Acknowledgement findAcknowledgement(String acknowledgement) {
        Assert.notNull(acknowledgement, "The Acknowledgement's data must not be null");
        return acknowledgements.get(acknowledgement);		
	}
}
