package com.cjet.payments.demo.ws.client;

import com.cjet.payments.demo.ws.client.schemas.Acknowledgement;
import com.cjet.payments.demo.model.Payment;

public interface SubmitPaymentService {
	public Acknowledgement submitRequest(Payment payment);
}
