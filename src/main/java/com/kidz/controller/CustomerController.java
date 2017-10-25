package com.kidz.controller;

import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.kidz.cart.model.Customer;
import com.kidz.login.model.Account;
import com.kidz.rpc.LoginService;
import com.kidz.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="/saveCustomer",method=RequestMethod.PUT)
	@CrossOrigin
	public Customer saveCustomer(@RequestBody Customer customer) {
		
		if(customer.getEmail()!=null && customer.getFirstName()!=null && customer.getAccount().getPassword()!=null && !customer.getEmail().equals(""))	
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
	public Customer getCustomerByEmail(@PathVariable String email) {
		
		return customerService.getCustomerByEmail(email);		
	
	}
	
	
	@CrossOrigin
	@PostMapping("/login")
	public String  redirectToTarget(@RequestBody Account account,HttpServletResponse response){
		
		if(!loginService.authenticateUser(account))
			return null;
		
		Customer customer=customerService.getCustomerByEmail(account.getUsername());		
		
		String str=customer.getId()+":"+customer.getCart().getId()+":"+customer.getEmail();
						
		return str;

	}

	@SuppressWarnings("rawtypes")
	@CrossOrigin
	@PostMapping("/logout")
	public ResponseEntity  logout(HttpServletRequest request,HttpServletResponse response){

		Cookie[] cookies = request.getCookies();
		if(cookies!=null)
			for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("ebuy")){
					cookies[i].setMaxAge(0);
					response.addCookie(cookies[i]);
				}	
			}
		
		//response.setHeader("Location", "/login");
	    
		return new ResponseEntity(org.springframework.http.HttpStatus.FOUND);

	}
}
