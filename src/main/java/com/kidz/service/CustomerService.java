package com.kidz.service;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kidz.cart.model.Customer;

public interface CustomerService {
	
	Customer getCustomerById(Long customerId);
	
	void save(Customer customer);
		
	Customer getCustomerByEmail(String email);
		
	Page<Customer> getAllCustomer(Pageable pageable,Map<String, Object> filterMap);
	
	void delete(Long customerId);
	
	List<Customer> getAllCustomer();
	
}
