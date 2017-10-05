package com.kidz.service;

import java.util.List;

import com.kidz.cart.model.Customer;

public interface CustomerService {
	
	Customer getCustomerById(Long customerId);
	
	void save(Customer customer);
		
	Customer getCustomerByEmail(String email);
		
	List<Customer> getAllCustomer();
	
	void delete(Long customerId);
}
