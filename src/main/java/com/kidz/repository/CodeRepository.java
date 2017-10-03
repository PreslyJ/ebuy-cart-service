package com.kidz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kidz.cart.model.Customer;
import com.kidz.model.Code;

public interface CodeRepository extends CrudRepository<Code, Long>{
	
	List<Code> findByCodeTypeAndCustomer(int codeType, Customer customer);
	
	Code findByCodeStr(String codeStr);
}
