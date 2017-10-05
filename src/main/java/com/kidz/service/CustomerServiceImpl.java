package com.kidz.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kidz.cart.model.Cart;
import com.kidz.cart.model.Code;
import com.kidz.cart.model.Customer;
import com.kidz.cart.model.Role;
import com.kidz.repository.CartRepository;
import com.kidz.repository.CustomerRepository;
import com.kidz.repository.RoleRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
    private CustomerRepository customerRepository;

	@Autowired
    private CartRepository cartRepository;

    public void save(Customer customer) {

    	// save or update
    	if(customer.getId() == null || customer.getId()<1){
    		Cart cart = new Cart();
        	
    		customerRepository.save(customer);
        	
    		// save cart
        	cart.setCustomer(customer);
        	cartRepository.save(cart);
        	
        	// update cartId in Customer
        	customer.setCart(cart);
        	customer.setEnabled(true);
        	
        	customerRepository.save(customer);
        	
    	}else
    		customerRepository.save(customer);
    	
    }
        
    public Customer getCustomerByEmail(String email){
    	return customerRepository.findByEmail(email);
    }
    
    public Customer getCustomerById(Long customerId){
    	return customerRepository.findOne(customerId);
    }
    
    
    public List<Customer> getAllCustomer(){
    	return (List<Customer>) customerRepository.findAll();
    }
    
    public void delete(Long customerId){
    	customerRepository.delete(customerId);
    }
}
