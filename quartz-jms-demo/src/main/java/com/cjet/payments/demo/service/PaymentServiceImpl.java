package com.cjet.payments.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cjet.payments.demo.ws.client.schemas.Acknowledgement;
import com.cjet.payments.demo.model.Payment;
import com.cjet.payments.demo.repository.PaymentRepository;
import com.cjet.payments.demo.ws.client.SubmitPaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository repository;
	
	@Autowired
    @Qualifier("submitPaymentServiceImpl")
	private SubmitPaymentService submitPaymentService;
	
	@Override
	public List<Payment> findAll() {
		List<Payment> payments = (List<Payment>) repository.findAll();
		return payments;
	}

	@Override
	public Acknowledgement submitPaymentRequest(Payment payment) {
		return submitPaymentService.submitRequest(payment);
	}

}
