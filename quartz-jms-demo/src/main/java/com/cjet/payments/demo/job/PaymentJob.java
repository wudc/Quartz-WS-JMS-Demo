package com.cjet.payments.demo.job;

import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cjet.payments.demo.ws.client.schemas.Acknowledgement;
import com.cjet.payments.demo.model.Payment;
import com.cjet.payments.demo.service.PaymentService;
import com.cjet.quartz.demo.util.QuartzJob;

@QuartzJob
@DisallowConcurrentExecution
public class PaymentJob implements Job {

	private static final Logger log = LoggerFactory.getLogger(PaymentJob.class);

	@Autowired
	PaymentService paymentService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("\n\n" + "============== Wake up and process request. Time:" + java.time.LocalTime.now());
		//test get payment from local repository
		testGetPaymentFromLocal();
		submitPaymentRequest();
	}
	
	private void submitPaymentRequest() {
		List<Payment> payments = paymentService.findAll();
		log.info("\n\n******* Payment submit acknowledgement:");
		
		for (Payment payment : payments) {	
			Acknowledgement ack = paymentService.submitPaymentRequest(payment);
			log.info("Ack Id: $" + ack.getId());
			log.info("Acknowledgement: " + ack.getAcknowledgement());
		}
	}
	
	private void testGetPaymentFromLocal() {
		List<Payment> payments = paymentService.findAll();
		log.info("\n\n######## Get payment data from local repository:");

		for (Payment payment : payments) {
			log.info("Amount: $" + payment.getAmount());
			log.info("Status: " + payment.getStatus());
			log.info("Method: " + payment.getMethod());
		}		
	}
}
