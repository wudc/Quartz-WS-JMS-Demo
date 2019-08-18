package com.cjet.payments.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cjet.payments.demo.model.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long>{

}
