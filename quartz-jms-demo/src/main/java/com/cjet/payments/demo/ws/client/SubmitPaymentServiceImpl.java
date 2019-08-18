package com.cjet.payments.demo.ws.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjet.payments.demo.ws.client.schemas.Acknowledgement;
import com.cjet.payments.demo.ws.client.schemas.GetPaymentRequest;
import com.cjet.payments.demo.ws.client.schemas.GetPaymentResponse;
import com.cjet.payments.demo.model.Payment;

@Service("submitPaymentServiceImpl")
public class SubmitPaymentServiceImpl implements SubmitPaymentService {

	@Autowired
	SOAPConnector soapConnector;
		
	@Override
	public Acknowledgement submitRequest(Payment payment) {
        GetPaymentRequest request = new GetPaymentRequest();
        request.setAmount(payment.getAmount());
        request.setStatus("Submitted");
        request.setMethod("Cash");
        GetPaymentResponse response =(GetPaymentResponse) soapConnector.callWebService("http://localhost:8080/ws", request);

		return response.getAcknowledgement();
	}

}
