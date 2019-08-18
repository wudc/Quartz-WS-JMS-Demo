package com.cjet.payments.demo.service;

import java.util.List;

import com.cjet.payments.demo.ws.client.schemas.Acknowledgement;
import com.cjet.payments.demo.model.Payment;

public interface PaymentService {
	List<Payment> findAll();
	Acknowledgement submitPaymentRequest(Payment payment);
}
