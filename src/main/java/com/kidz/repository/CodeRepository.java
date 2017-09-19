package com.kidz.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kidz.model.Code;
import com.kidz.model.Customer;

public interface CodeRepository extends CrudRepository<Code, Long>{
	
	List<Code> findByCodeTypeAndCustomer(int codeType, Customer customer);
	
	Code findByCodeStr(String codeStr);
}
