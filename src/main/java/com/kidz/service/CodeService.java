package com.kidz.service;

import java.util.List;

import com.kidz.cart.model.Code;
import com.kidz.cart.model.Customer;

public interface CodeService {
	
	List<Code> findByCodeTypeAndCustomer(int codeType, Customer customer);
	
	void save(Code code);
	
	Code findByCodeStr(String codeStr);
	
	void delete(Code code);
}
