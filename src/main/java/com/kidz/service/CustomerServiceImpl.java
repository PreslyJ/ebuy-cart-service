package com.kidz.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidz.cart.model.Cart;
import com.kidz.cart.model.Customer;
import com.kidz.repository.CartRepository;
import com.kidz.repository.CustomerRepository;

@Transactional 
@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
    private CustomerRepository customerRepository;

	@Autowired
    private CartRepository cartRepository;

    public void save(Customer customer) {

    	// save or update
    	if(customer.getId()<1){
    	
    		Cart cart = new Cart();
        	
    		customer.getAccount().setPassword(new BCryptPasswordEncoder().encode(customer.getAccount().getPassword()));
    		
    		customerRepository.save(customer);
        	
    		// save cart
        	cart.setCustomer(customer);
        	cartRepository.save(cart);
        	
        	// update cartId in Customer
        	customer.setCart(cart);
        	
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
