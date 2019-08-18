package com.cjet.demo.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.cjet.demo.repository.PaymentRepository;

import https.www_cjet_com.xml.payment.GetPaymentRequest;
import https.www_cjet_com.xml.payment.GetPaymentResponse;

@Endpoint
public class PaymentEndpoint {
	private static final String NAMESPACE_URI = "https://www.cjet.com/xml/payment";

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentEndpoint(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPaymentRequest")
    @ResponsePayload
    public GetPaymentResponse getAcknowledgement(@RequestPayload GetPaymentRequest request) {
        GetPaymentResponse response = new GetPaymentResponse();
        if ( request.getAmount() > 10000.0 && request.getMethod().equals("Cash")) {
        	response.setAcknowledgement(paymentRepository.findAcknowledgement("Rejected"));
        }
        else if ( request.getAmount() > 10000.0 && request.getMethod().equals("Credit") ) {
        	response.setAcknowledgement(paymentRepository.findAcknowledgement("Pending"));        	
        }
        else {
        	response.setAcknowledgement(paymentRepository.findAcknowledgement("Accepted"));        	       	
        }
        return response;
    }
}
