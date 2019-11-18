package com.cjet.demo.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.cjet.demo.model.Payments;

@Repository
public interface PaymentsRepository {
	
	@Select("Select * from payments")
	public List<Payments> findAll();
}
