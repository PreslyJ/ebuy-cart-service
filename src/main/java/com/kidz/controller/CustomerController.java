package com.kidz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.kidz.cart.model.Customer;
import com.kidz.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value="/saveCustomer",method=RequestMethod.PUT)
	@CrossOrigin
	public Customer saveCustomer(@RequestBody Customer customer) {

		customerService.save(customer);
		
		return customer;		
	}
	
	@RequestMapping(value="/getAllCustomer",method=RequestMethod.GET)
	@CrossOrigin
	public List<Customer> getAllCustomers() {
	
		return customerService.getAllCustomer();		

	}
	
	@RequestMapping(value="/getCustomer/{customerId}",method=RequestMethod.GET)
	@CrossOrigin
	public Customer getCustomerById(@PathVariable long customerId) {
		
		return customerService.getCustomerById(customerId);		
	
	}
	
	@RequestMapping(value="/getCustomerByEmail/{email}",method=RequestMethod.GET)
	@CrossOrigin
	public Customer getCustomerById(@PathVariable String email) {
		
		return customerService.getCustomerByEmail(email);		
	
	}
	
	
	
}
